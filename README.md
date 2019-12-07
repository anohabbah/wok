#Au wok
Ce projet est composé de deux parties. La première partie peut fonctionner sans la seconde
(mais pas l’inverse). Lorsque vous commencerez la deuxième partie, faites une sauvegarde de la
première à toute fin utile.

##Partie 1 : Simulation multithreadée
Un restaurant vous demande de simuler son fonctionnement en vue d’améliorer la fluidité
de son service. Si le but n’est pas ici d’avoir une simulation hautement réaliste, il sera toutefois
nécessaire de correctement simuler les situations de concurrences entre les clients, entre les clients
et les employés, etc.<br>
Votre programme principal, Restaurant, représente le restaurant, qui a un nombre de places
limité à 25 (le nombre de client total peut dépasser ce nombre : lorsque le restaurant est plein, les
nouveaux clients attendent que d’autres sortent.) En plus de cette capacité limitée, le restaurant
devra contenir, initialiser et lancer les éléments suivants :<br>
**Un buffet.** Le buffet contient quatre bacs, chacun contenant initialement 1kg de nourriture
crue différente. Plus précisément, il y a :
 - un bac de 1kg de poisson cru ;
 - un bac de 1kg de viande cru ;
 - un bac de légumes crus ; 
 - un bac de 1kg de nouilles froides.

Il doit être possible d’accéder en même temps au buffet si ce n’est pas dans le même bac. En
revanche, deux entités actives ne pourront pas accéder en même temps au même bac. Un aspect
important des bacs est le fait que, comme on l’a dit, ils ont une capacité limitée de 1kg. A chaque
fois qu’un client se sert, la quantité de nourriture restante dans le bac est diminué du poids de
la portion prise par ce client. Aussi, un client qui souhaite plus d’un ingrédient que ce qu’il reste
dans le bac correspondant devra attendre qu’il soit réapprovisionné, par l’employé du buffet.

**L’employé du buffet.** L’employé est chargé de veiller à ce qu’il reste suffisamment de nourriture dans chacun des compartiments. Pour cela, il fait le tour des compartiments afin de vérifier
les quantités restantes de chacun : si un compartiment a moins de 100g de nourriture restante, il
le remplit (afin que sa nouvelle quantité soit de nouveau de 1kg). L’employé n’est jamais à court
de nourriture. Il est important de veiller à ce que l’employé ne remplisse pas un bac alors qu’un
client est en train de se servir (sauf bien sˆur si ce client est en train d’en attendre le remplissage.)

**Le stand de cuisson.** Le stand de cuisson est l’endroit ou les clients doivent passer après avoir
rempli leur assiette au buffet, afin de faire cuire ce qu’ils y ont pris. Le stand de cuisson est le
lieu o`u doivent se synchroniser le cuisinier et les clients, selon les règles qui sont décrites par la
suite.

**Un cuisinier.** Le cuisinier est là pour faire cuire les plats des clients une fois que ceux-ci ont
leur assiette pleine de nourriture crue prise dans le buffet. Le cuisinier devra être simulé comme
une entité active : il doit se synchroniser avec les clients (au niveau du stand de cuisson) pour
faire cuire leur plat un par un (sans forcément respecter l’ordre d’arrivée des clients à son stand).
Le cuisinier attend un client, fait cuire son plat (il ne peut en faire cuire qu’un seul à la fois), le
notifie une fois que c’est cuit et attend le client suivant.

**Des Clients.** Le nombre de client total pour la simulation sera d’au moins 50. Chaque client
devra être simulé comme une entité autonome qui réalisera les actions suivantes, dans cette ordre :
1. Entrer dans le restaurant (c’est à dire attendre qu’une place se libère si besoin) ;
2. Se rendre au buffet et prendre une portion d’un poids aléatoire entre 0 et 100g de chacun
des ingrédient ; Il n’est pas nécessaire de simuler la prise de couvert. Chaque prise de portion
prendra entre 200 et 300 ms. Comme dit plus haut, le client devra attendre si la quantité
restante de nourriture est inférieure à ce qu’il souhaite.
3. Faire la queue au stand de cuisson (il ne sera pas nécessaire de simuler une file d’attente),
puis attendre la fin de la cuisson de son plat ;
4. Manger (un temps fini, pas de contraintes particulières ici) ;
5. Sortir (et donc juste libérer une place, pas besoin de modéliser le paiement).

##Partie 2 : API Rest de suivi
Une fois que votre simulation vous semble satisfaisante, vous devrez développer une API
Rest permettant de la contrˆoler. Plus précisément, en utilisant la librairie Restlet, vous devrez
développer une API permettant de :
 - lister les clients avec leur état (GET /client/{client_id})
 - retourner l’état du buffet (GET /buffet).
 
 Les clients auront successivement les états suivants :
   1. `WAITING_TO_ENTER` - en attente pour rentrer
   2. `AT_THE_BUFFET` - en train de se servir (ou d’attendre un remplissage)
   3. `WAITING_THE_COOK` - en attente du cuisinier ou de la cuisson de son plat
   4. `EATING` - en train de manger
   5. `OUT` - sorti.<br>
 L’état du buffet doit retourner pour chaque compartiment, la quantité de nourriture restante.

##Conseils et instructions
Une fois votre partie 1 finie, le plus simple est de repartir du code du TP4 sur Restlet et
de remplacer la classe InMemoryDatabae par votre classe Restaurant et de mettre toutes vos
classes de la partie 1 dans le package Backend. Votre main de la partie 1 ne sera plus utilisée.
