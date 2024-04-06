<center>
 <p>La gestion de transport en ligne</p>
  <h1>Spring Boot + nextjs</h1>
<img src="./images/maxresdefault.jpg" alt="transport image">
</center>

# Configuration de la fichier application.properties

# Chemins vers le fichier application.properties
paths:
  /src/main/resources/application.properties
  /src/test/resources/application.properties
  
# Configuration du port du serveur
server.port=8080

# Contexte de servlet pour l'API
server.servlet.context-path=/api

# Configuration de la source de données PostgreSQL
spring.datasource.url=jdbc:postgresql://localhost:5432/transport_management_system
spring.datasource.username=nom_utilisateur
spring.datasource.password=mot_de_passe
spring.datasource.driver-class-name=org.postgresql.Driver

# Stratégie de mise à jour du schéma de la base de données
spring.jpa.hibernate.ddl-auto=update

# Affichage des requêtes SQL dans la console
spring.jpa.show-sql=true
