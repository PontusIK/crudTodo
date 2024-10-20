# Inlämningsuppgift - Skapa en CI/CD Pipeline med GitHub Actions och AWS

## Pontus Kävrestad

### om API:et

Jag har skapat ett api för att hantera todo-entiteter  
som skulle pass bra till en "att göra lista" typ av klient.  
Tyvärr han jag inte med att göra en sådan klient.

För att underlätta testerna flyttade jag så mycket av logiken  
i koden till serviceklassen.  
Det som syns i controllerklassen är endast "happy path" och fel  
hanteras med att exceptions kastas från serviceklassen som spring  
sedan tar och skapar en response med.

Eftersom jag bara har 1 entitet och därför inga relationer valde jag  
att använda en dokumentbaserad databas, nämligen MongoDb.  
Men senare ändrade jag databasen till MySQL på grund av bekymmer med  
AWS.

### AWS

Eftersom jag hade valt att använda mongoDB kunde jag inte använda RDS  
och därför fick det bli amazons DocumentDB. Dock kunde jag inte skapa
en anslutning till databasen och fick nedanstående felmeddelande av Spring.  

> Connection refused: no further information

Jag lyckades inte hitta en bättre lösning än att byta till MySQL.  
Dessutom var DocumentDB inte tillgänglig i sverige så att byta till MySQL  
lät mig byta servern till den närmast mig.

Annars följde jag vad som visades på lektionen 18/9 1-1 tills jag fick problem
med Elastic Beanstalk Launch Templates.  
Default inställningarna för Elastic Beanstalk är inte tillåtna för nya användare  
av AWS och använder man dem kommer ens application environment inte fungera.

> New accounts – New accounts will temporarily need to set at least one of the  
> [options listed](https://docs.aws.amazon.com/elasticbeanstalk/latest/dg/environments-cfg-autoscaling-launch-templates.html#environments-cfg-autoscaling-launch-templates-options) 
in Option settings for launch templates to successfully create a new environment.  
> The Auto Scaling service will only create environments for new accounts using launch templates.

### Endpoints

#### Base-URL: http://crudtodoweb-env-1.eba-qrmbpadq.eu-north-1.elasticbeanstalk.com

* POST /api/todo

    {  
        "title": "todo-title",  
        "description": "todo-description",  
        "deadLine": "yyyy-mm-dd"  
    }

* GET /api/todo/{id}
* GET /api/todo/all
* PUT /api/todo/

    {  
        "id": <id för todo som ska uppdateras eg. 1>,  
        "title": "todo-title",  
        "description": "todo-description",  
        "creationDate": "yyyy-mm-dd",  
        "deadLine": "yyyy-mm-dd",  
        "completed": true  
    }

* DELETE /api/todo/{id}