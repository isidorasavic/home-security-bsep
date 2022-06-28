package com.ftn.adminbackend.service;


import java.io.*;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


import com.ftn.adminbackend.dto.TextMessage;
import com.ftn.adminbackend.model.Certificate;
import com.ftn.adminbackend.model.enums.CertificateStatus;
import com.ftn.adminbackend.model.enums.RevokeReason;
import com.ftn.adminbackend.repository.CertificateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CertificateRevokeService {
    @Autowired
    CertificateRepository certificateRepository;

    @Autowired
    LoggerService loggerService;

    @Autowired
    CertificateViewService certificateViewService;


     public TextMessage revokeCertificate(String serialNumber, RevokeReason revokeReason) {
         Certificate certificate = certificateRepository.findBySerialNumber(serialNumber);
         if(certificate.getCertificateStatus() != CertificateStatus.REVOKED) {
             certificate.setRevokeReason(revokeReason);
             certificate.setIsActive(false);
             certificate.setCertificateStatus(CertificateStatus.REVOKED);

             Collection<Certificate> issuedCertificates = certificate.getCertificateChildren();
             for (Certificate issuedCertificate : issuedCertificates) {
                 switch (revokeReason) {
                     case EXPIRED:
                         revokeCertificate(issuedCertificate.getSerialNumber(), RevokeReason.EXPIRED);
                         break;
                     case KEY_COMPROMISE:
                     case CA_COMPROMISE:
                         revokeCertificate(issuedCertificate.getSerialNumber(), RevokeReason.CA_COMPROMISE);
                         break;
                     default:
                         revokeCertificate(issuedCertificate.getSerialNumber(), RevokeReason.UNKNOWN);
                         break;
                 }
             }

             certificateRepository.save(certificate);

             ObjectOutputStream outputStream = null;
             File file = null;

             try {
                file = new File("./revokedCerts/revokedCerts.rev");
                X509Certificate certificate1 = null;
                List<X509Certificate> certificateList = new ArrayList<>();

                if(certificate.getIsCA()) {
                    certificate1 = certificateViewService.getCertificate("ca", "bsep", certificate.getSerialNumber());
                } else {
                    certificate1 = certificateViewService.getCertificate("end-entity", "bsep", certificate.getSerialNumber());
                }

                if(file.exists()) {
                    ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file));
                    certificateList = (List<X509Certificate>) objectInputStream.readObject();
                    objectInputStream.close();
                }

                certificateList.add(certificate1);

                ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
                objectOutputStream.writeObject(certificateList);
                objectOutputStream.flush();
                objectOutputStream.close();
             } catch (FileNotFoundException ex) {
                 ex.printStackTrace();
             } catch (IOException ex) {
                 ex.printStackTrace();
             } catch (ClassNotFoundException e) {
                 e.printStackTrace();
             } finally {
                 //Close the ObjectOutputStream
                 try {
                     if (outputStream != null) {
                         outputStream.flush();
                         outputStream.close();
                     }
                 } catch (IOException ex) {
                     ex.printStackTrace();
                 }
             }
             this.revokeFromKeyStore(certificate.getSerialNumber(), certificate.getIsCA());

             loggerService.print("Certificate " + serialNumber + " successfully revoked.");
             return new TextMessage("Certificate " + serialNumber + " successfully revoked.");
         }
         else {
             loggerService.print("Certificate " + serialNumber + " revocation failed.");
             return new TextMessage("Certificate " + serialNumber + " revocation failed..");
         }
        }

        private void revokeFromKeyStore(String serialNumber, Boolean isCA) {
            String alias = null;
            char [] password = {'b','s','e','p'};
            try {
                KeyStore keyStore = KeyStore.getInstance("PKCS12");
                if(isCA) {
                    keyStore.load(new FileInputStream("./keystore/keystoreCA.p12"), password);
                } else {
                    keyStore.load(new FileInputStream("./keystore/keystoreEE.p12"), password);
                }
                System.out.println("Keystore size before deleting: " + keyStore.size());
                keyStore.deleteEntry(serialNumber);
                System.out.println("Keystore size after deleting: " + keyStore.size());
            } catch (KeyStoreException e) {
                e.printStackTrace();
            } catch (CertificateException e) {
                e.printStackTrace();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
}
