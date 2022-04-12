package com.homesecurity.service;

import org.bouncycastle.asn1.x500.X500NameBuilder;
import org.bouncycastle.asn1.x500.style.BCStyle;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.X509v3CertificateBuilder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.cert.jcajce.JcaX509v3CertificateBuilder;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;

import com.homesecurity.dto.SubjectDataDTO;
import com.homesecurity.model.IssuerData;
import com.homesecurity.model.SubjectData;

import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.SecureRandom;
import java.security.Security;
import java.security.SignatureException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class GenerateSertificateService {
	
	public X509Certificate generateSertificate(SubjectDataDTO subjectDataDTO) {
		
		SubjectData subjectData = generateSubjectData(subjectDataDTO);

        KeyPair keyPairIssuer = generateKeyPair();
        IssuerData issuerData = null;
//        IssuerData issuerData = generateIssuerData(keyPairIssuer.getPrivate());

        try {
            JcaContentSignerBuilder builder = new JcaContentSignerBuilder("SHA256WithRSAEncryption");

            builder = builder.setProvider("BC");
            ContentSigner contentSigner = builder.build(issuerData.getPrivateKey());

            // Postavljaju se podaci za generisanje sertifiakta
            X509v3CertificateBuilder certGen = new JcaX509v3CertificateBuilder(issuerData.getX500name(),
                    new BigInteger(subjectData.getSerialNumber()),
                    subjectData.getStartDate(),
                    subjectData.getEndDate(),
                    subjectData.getX500name(),
                    subjectData.getPublicKey());
            // Generise se sertifikat
            X509CertificateHolder certHolder = certGen.build(contentSigner);

            JcaX509CertificateConverter certConverter = new JcaX509CertificateConverter();
            certConverter = certConverter.setProvider("BC");

            return certConverter.getCertificate(certHolder);
        } catch (IllegalArgumentException | IllegalStateException | OperatorCreationException | CertificateException e) {
            e.printStackTrace();
        }
        return null;

	}
	
	public SubjectData generateSubjectData(SubjectDataDTO subjectData) {
        try {
            KeyPair keyPairSubject = generateKeyPair();

            // Datumi od kad do kad vazi sertifikat
            SimpleDateFormat iso8601Formater = new SimpleDateFormat("yyyy-MM-dd");
            LocalDate now = LocalDate.now();
            Date startDate = iso8601Formater.parse(now.toString());
            Date endDate = iso8601Formater.parse(now.withYear(now.getYear()+5).toString());

            // Serijski broj sertifikata  TODO: ispraviti
            String sn = "1";

            // klasa X500NameBuilder pravi X500Name objekat koji predstavlja podatke o vlasniku
            X500NameBuilder builder = new X500NameBuilder(BCStyle.INSTANCE);
            builder.addRDN(BCStyle.CN, subjectData.getCN());
            builder.addRDN(BCStyle.SURNAME, subjectData.getSurname());
            builder.addRDN(BCStyle.GIVENNAME, subjectData.getGivenname());
            builder.addRDN(BCStyle.O, subjectData.getO());
            builder.addRDN(BCStyle.OU, subjectData.getOU());
            builder.addRDN(BCStyle.C, subjectData.getC());
            builder.addRDN(BCStyle.E, subjectData.getE());
            builder.addRDN(BCStyle.UID, subjectData.getUID());

            return new SubjectData(keyPairSubject.getPublic(), builder.build(), sn, startDate, endDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;

	}
	
    private KeyPair generateKeyPair() {
        try {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");
            keyGen.initialize(2048, random);
            return keyGen.generateKeyPair();
        } catch (NoSuchAlgorithmException | NoSuchProviderException e) {
            e.printStackTrace();
        }
        return null;
    }

}
