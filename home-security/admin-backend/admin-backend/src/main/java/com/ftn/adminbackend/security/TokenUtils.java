package com.ftn.adminbackend.security;

import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import com.ftn.adminbackend.model.User;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.Claims;

@Component
public class TokenUtils {

	// Izdavac tokena
	@Value("spring-security-example")
	private String APP_NAME;

	// Tajna koju samo backend aplikacija treba da zna kako bi mogla da generise i
	// proveri JWT https://jwt.io/
	@Value("somesecret")
	public String SECRET;

	// Period vazenja
	// 7 days
	@Value("1036800000")
	private long EXPIRES_IN;

	// Naziv headera kroz koji ce se prosledjivati JWT u komunikaciji server-klijent
	@Value("Authorization")
	private String AUTH_HEADER;

	// Moguce je generisati JWT za razlicite klijente (npr. web i mobilni klijenti
	// nece imati isto trajanje JWT, JWT za mobilne klijente ce trajati duze jer se
	// mozda aplikacija redje koristi na taj nacin)
	private static final String AUDIENCE_UNKNOWN = "unknown";
	private static final String AUDIENCE_WEB = "web";
	private static final String AUDIENCE_MOBILE = "mobile";
	private static final String AUDIENCE_TABLET = "tablet";

	// Algoritam za potpisivanje JWT
	private SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS512;

	// Funkcija za generisanje JWT token
	public String generateToken(String username) {
		return Jwts.builder().setIssuer(APP_NAME).setSubject(username).setAudience(generateAudience())
				.setIssuedAt(new Date()).setExpiration(generateExpirationDate())
				// .claim("key", value) //moguce je postavljanje proizvoljnih podataka u telo
				// JWT tokena
				.signWith(SIGNATURE_ALGORITHM, SECRET).compact();
	}

	private String generateAudience() {
		// Moze se iskoristiti org.springframework.mobile.device.Device objekat za
		// odredjivanje tipa uredjaja sa kojeg je zahtev stigao.

		// String audience = AUDIENCE_UNKNOWN;
		// if (device.isNormal()) {
		// audience = AUDIENCE_WEB;
		// } else if (device.isTablet()) {
		// audience = AUDIENCE_TABLET;
		// } else if (device.isMobile()) {
		// audience = AUDIENCE_MOBILE;
		// }
		return AUDIENCE_WEB;
	}

	private Date generateExpirationDate() {
		return new Date(new Date().getTime() + EXPIRES_IN);
	}

	// Funkcija za refresh JWT tokena
	public String refreshToken(String token) {
		String refreshedToken;
		try {
			final Claims claims = this.getAllClaimsFromToken(token);
			claims.setIssuedAt(new Date());
			refreshedToken = Jwts.builder().setClaims(claims).setExpiration(generateExpirationDate())
					.signWith(SIGNATURE_ALGORITHM, SECRET).compact();
		} catch (Exception e) {
			refreshedToken = null;
		}
		return refreshedToken;
	}

	public boolean canTokenBeRefreshed(String token, Date lastPasswordReset) {
		final Date created = this.getIssuedAtDateFromToken(token);
		return (!(this.isCreatedBeforeLastPasswordReset(created, lastPasswordReset))
				&& (!(this.isTokenExpired(token)) || this.ignoreTokenExpiration(token)));
	}

	// Funkcija za validaciju JWT tokena
	public Boolean validateToken(String token, UserDetails userDetails) {
		User user = (User) userDetails;
		final String username = getUsernameFromToken(token);
		final Date created = getIssuedAtDateFromToken(token);

		//TODO: dodati is not deleted!
		return (username != null && username.equals(userDetails.getUsername()));
	}

	public String getUsernameFromToken(String token) {
		String username;
		try {
			final Claims claims = this.getAllClaimsFromToken(token);
			username = claims.getSubject();
		} catch (Exception e) {
			username = null;
		}
		return username;
	}

	public Date getIssuedAtDateFromToken(String token) {
		Date issueAt;
		try {
			final Claims claims = this.getAllClaimsFromToken(token);
			issueAt = claims.getIssuedAt();
		} catch (Exception e) {
			issueAt = null;
		}
		return issueAt;
	}

	public String getAudienceFromToken(String token) {
		String audience;
		try {
			final Claims claims = this.getAllClaimsFromToken(token);
			audience = claims.getAudience();
		} catch (Exception e) {
			audience = null;
		}
		return audience;
	}

	public Date getExpirationDateFromToken(String token) {
		Date expiration;
		try {
			final Claims claims = this.getAllClaimsFromToken(token);
			expiration = claims.getExpiration();
		} catch (Exception e) {
			expiration = null;
		}
		return expiration;
	}

	public long getExpiredIn() {
		return EXPIRES_IN;
	}

	// Funkcija za preuzimanje JWT tokena iz zahteva
	public String getToken(HttpServletRequest request) {
		Cookie[] maybeCookies = getCookies(request);
		String cookieName = "accessToken";

		// JWT se prosledjuje kroz cookie u formatu:
		// eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c
		return Optional.ofNullable(maybeCookies).flatMap(
				cookies -> Arrays.stream(cookies).filter(cookie -> cookieName.equals(cookie.getName())).findAny())
				.map(cookie -> cookie.getValue()).orElse(null);
	}

	public Cookie[] getCookies(HttpServletRequest request) {
		return request.getCookies();
	}

	private Boolean isCreatedBeforeLastPasswordReset(Date created, Date lastPasswordReset) {
		return (lastPasswordReset != null && created.before(lastPasswordReset));
	}

	private Boolean isTokenExpired(String token) {
		final Date expiration = this.getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}

	private Boolean ignoreTokenExpiration(String token) {
		String audience = this.getAudienceFromToken(token);
		return (audience.equals(AUDIENCE_TABLET) || audience.equals(AUDIENCE_MOBILE));
	}

	// Funkcija za citanje svih podataka iz JWT tokena
	private Claims getAllClaimsFromToken(String token) {
		Claims claims;
		try {
			claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
		} catch (Exception e) {
			claims = null;
		}
		return claims;
	}

}
