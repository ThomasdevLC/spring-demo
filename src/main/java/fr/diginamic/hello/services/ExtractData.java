package fr.diginamic.hello.services;


import fr.diginamic.hello.entites.Departement;
import fr.diginamic.hello.entites.Ville;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;


@Component
public class ExtractData implements CommandLineRunner {

    @Autowired
    private VilleService villeService;

    public static void main(String[] args) {
        SpringApplication.run(ExtractData.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        String fileName = "recensement.csv";
        URL resourceUrl = ExtractData.class.getClassLoader().getResource(fileName);

        if (resourceUrl == null) {
            System.out.println("Le fichier recensement n'existe pas ");
            return;
        }

        Path path;
        try {
            path = Paths.get(resourceUrl.toURI());
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la récupération du chemin du fichier.", e);
        }

        List<String> allLines = Files.readAllLines(path);
        List<String> lignes = allLines.subList(1, allLines.size());

        for (String ligne : lignes) {
            String[] col = ligne.split(";");

            if (col.length >= 8) {
                String nomCommune = col[6];
                String population = col[7].replace(" ", "").trim();
                int populationTotale = Integer.parseInt(population);
                String codeDepartementString = col[2].trim();
                String codeDepartementDigits = codeDepartementString.replaceAll("[^\\d]", "");
                int codeDepartement = Integer.parseInt(codeDepartementDigits); 
                
                Departement departement = new Departement(codeDepartement);

              
                Ville ville = new Ville(nomCommune, populationTotale, departement);
                
                if (ville.getPopulation()>= 10000) {

                villeService.insertVille(ville);}

                System.out.println(ville.toString());
            } else {
                System.out.println("La ligne ne contient pas assez de colonnes : " + ligne);
            }
        }
    }
}


