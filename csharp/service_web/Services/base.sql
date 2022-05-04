select (sum(panneau.prix) / count(*)) as prixmoyenne, count(*) as quantite,intervale.debut, intervale.fin , (sum(panneau.prix/panneau.puissance) / count(*)) as prixwatt  from panneau 
inner join intervale  
on panneau.puissance > intervale.debut 
and panneau.puissance <= intervale.fin
group by intervale.debut, intervale.fin ;