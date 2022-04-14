package com.ftn.adminbackend.service;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import org.bouncycastle.asn1.x500.X500NameBuilder;
import org.bouncycastle.asn1.x500.style.BCStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.adminbackend.dto.CSRDTO;
import com.ftn.adminbackend.model.SubjectData;
import com.ftn.adminbackend.repository.CSRRepository;

@Service
public class CSRService {

    @Autowired
    private CSRRepository csrRepository;

    public String generateCSR(CSRDTO csr){
        try {
            KeyPair keyPairSubject = generateKeyPair();

            // Datumi od kad do kad vazi sertifikat
            SimpleDateFormat iso8601Formater = new SimpleDateFormat("yyyy-MM-dd");
            LocalDate now = LocalDate.now();
            Date startDate = iso8601Formater.parse(now.toString());
            Date endDate = iso8601Formater.parse(now.withYear(now.getYear()+5).toString());

            // Serijski broj sertifikata
            // TODO: sigurno izmeniti :')
            String sn = "1";

            // klasa X500NameBuilder pravi X500Name objekat koji predstavlja podatke o vlasniku
            X500NameBuilder builder = new X500NameBuilder(BCStyle.INSTANCE);
            builder.addRDN(BCStyle.CN, csr.getCN());
            builder.addRDN(BCStyle.SURNAME, csr.getSurname());
            builder.addRDN(BCStyle.GIVENNAME, csr.getGivenName());
            builder.addRDN(BCStyle.O, csr.getO());
            builder.addRDN(BCStyle.OU, csr.getOU());
            builder.addRDN(BCStyle.C, csr.getC());
            builder.addRDN(BCStyle.E, csr.getEmailAddress());
            // TODO: mozda izmeniti
            builder.addRDN(BCStyle.UID, csr.getUsername());

            // Kreiraju se podaci za sertifikat, sto ukljucuje:
            // - javni kljuc koji se vezuje za sertifikat
            // - podatke o vlasniku
            // - serijski broj sertifikata
            // - od kada do kada vazi sertifikat
            SubjectData sd = new SubjectData(keyPairSubject.getPublic(), builder.build(), sn, startDate, endDate);

            // CSR csrFile = new CSR(sn, sd.toString(), false);
            // csrRepository.save(csrFile);

            return sd.toString();
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
