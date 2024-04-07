<center>
 <p>La gestion de transport en ligne</p>
  <h1>Spring Boot + nextjs</h1>
<img src="./images/maxresdefault.jpg" alt="transport image">
</center>

# Configuration de la fichier application.properties

#### Chemins vers le fichier application.properties
paths:
  /src/main/resources/application.properties
  /src/test/resources/application.properties
  
#### contenu de la fichier application.properties
server.port=8080

server.servlet.context-path=/api

spring.datasource.url=jdbc:postgresql://localhost:5432/transport_management_system

spring.datasource.username=nom_utilisateur

spring.datasource.password=mot_de_passe

spring.datasource.driver-class-name=org.postgresql.Driver

spring.jpa.hibernate.ddl-auto=update

spring.jpa.show-sql=true

###### https://generate-random.org/encryption-key-generator?count=1&bytes=32&cipher=aes-256-cbc&string=&password=
jwt.secret.key=
