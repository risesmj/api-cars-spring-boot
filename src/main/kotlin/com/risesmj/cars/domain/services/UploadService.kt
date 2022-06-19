package com.risesmj.cars.domain.services

import com.google.auth.oauth2.GoogleCredentials
import com.google.cloud.storage.Acl
import com.google.cloud.storage.Blob
import com.google.cloud.storage.Bucket
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.cloud.StorageClient
import com.risesmj.cars.domain.dto.UploadInputDTO
import org.springframework.stereotype.Service
import java.io.FileInputStream
import java.util.Base64
import javax.annotation.PostConstruct


@Service
class UploadService {

    @PostConstruct
    fun init(){

        if(FirebaseApp.getApps().isEmpty()) {
            val serviceAccount = FileInputStream("src/main/resources/serviceAccountKey.json")

            val options = FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setStorageBucket("spring-boot-cars.appspot.com")
                .build()

            FirebaseApp.initializeApp(options)
        }

    }

    fun upload(uploadInputDTO: UploadInputDTO): String{
        var bucket: Bucket = StorageClient.getInstance().bucket()

        //Decodifica os bytes em base64
        var bytes = Base64.getDecoder().decode(uploadInputDTO.base64)

        //Cria o arquivo via protocolo blob, utilizando os dados recebidos do request
        var blob: Blob = bucket
            .create(
                uploadInputDTO.fileName,
                bytes,
                uploadInputDTO.mimeType
            )

        //Cria o arquivo público com permissão de apenas leitura para todos os usuários
        blob.createAcl(Acl.of(Acl.User.ofAllUsers(), Acl.Role.READER))

        return String.format("https://storage.googleapis.com/%s/%s", bucket.name, uploadInputDTO.fileName)
    }
}