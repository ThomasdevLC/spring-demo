package fr.diginamic.hello.startup;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import fr.diginamic.hello.entites.DepartementApi;
import fr.diginamic.hello.utils.DepartementCodeUtil;

import org.springframework.http.ResponseEntity;
import java.util.HashMap;
import java.util.Map;

@Component
public class ExtractFromApi implements CommandLineRunner {

    private static Map<Integer, DepartementApi> departementMap = new HashMap<>();

    @Override
    public void run(String... args) throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = "https://geo.api.gouv.fr/departements?fields=nom,code,codeRegion";

        try {
            ResponseEntity<DepartementApi[]> response = restTemplate.getForEntity(apiUrl, DepartementApi[].class);

            if (response.getStatusCode().is2xxSuccessful()) {
                DepartementApi[] departements = response.getBody();
                System.out.println("Réponse de l'API : ");
                for (DepartementApi departement : departements) {
                    int codeDepartement = DepartementCodeUtil.parseCodeDepartement(departement.getCode());
                    departementMap.put(codeDepartement, departement);
                    System.out.println(departement);
                }
            } else {
                System.err.println("Erreur lors de l'extraction des données de l'API");
            }
        } catch (Exception e) {
            System.err.println("Erreur lors de l'appel à l'API externe : " + e.getMessage());
        }
    }

    public Map<Integer, DepartementApi> getDepartementMap() {
        return departementMap;
    }
}
