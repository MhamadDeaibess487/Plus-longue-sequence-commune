import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class RecherchePLSSC {

    // Recherche d'une PLSSC de 2 chaînes, naïf
    // Naïf optimisé : pas de DP, pas de memoization
    static String PLSSC(String S1, String S2) {
        return plsscRec(S1, S2, 0, 0, new StringBuilder(), new StringBuilder());
    }

    private static String plsscRec(String S1, String S2,
                                int i, int j,
                                StringBuilder courant,
                                StringBuilder meilleur) {

        // Borne supérieure : même en prenant tout ce qui reste,
        // on ne peut pas battre la meilleure solution courante
        int maxPossible = Math.min(S1.length() - i, S2.length() - j);
        if (courant.length() + maxPossible <= meilleur.length()) {
            return meilleur.toString();
        }

        // Fin d'une des chaînes
        if (i == S1.length() || j == S2.length()) {
            if (courant.length() > meilleur.length()) {
                return courant.toString();
            }
            return meilleur.toString();
        }

        // Si les caractères correspondent, on les prend (branche prioritaire)
        if (S1.charAt(i) == S2.charAt(j)) {
            courant.append(S1.charAt(i));
            String res = plsscRec(S1, S2, i + 1, j + 1, courant, meilleur);
            courant.deleteCharAt(courant.length() - 1);
            if (res.length() > meilleur.length()) {
                meilleur.setLength(0);
                meilleur.append(res);
            }
            return meilleur.toString();
        }

        // Sinon, on explore les deux branches (sans substring)
        // Branche 1 : avancer dans S1
        String res1 = plsscRec(S1, S2, i + 1, j, courant, meilleur);
        if (res1.length() > meilleur.length()) {
            meilleur.setLength(0);
            meilleur.append(res1);
        }

        // Branche 2 : avancer dans S2
        String res2 = plsscRec(S1, S2, i, j + 1, courant, meilleur);
        if (res2.length() > meilleur.length()) {
            meilleur.setLength(0);
            meilleur.append(res2);
        }

        return meilleur.toString();
    }


    // Recherche d'une PLSSC de 2 chaînes, prog. dyn.
    static String PLSSC_PD(String S1, String S2) {
        int [][]tab=new int[S1.length()+1][S2.length()+1];
        if (S1.length() == 0 || S2.length() == 0) {
            return "";
        }
        for(int i=0;i<=S1.length();i++){
            tab[i][0]=0;
        }
        for(int j=0;j<=S2.length();j++){
            tab[0][j]=0;
        }
        for(int i=1;i<=S1.length();i++){
            for(int j=1;j<=S2.length();j++){
                if(S1.charAt(i-1)==S2.charAt(j-1)){
                    tab[i][j]=tab[i-1][j-1]+1;
                }else{
                    tab[i][j]=Math.max(tab[i-1][j],tab[i][j-1]);
                }
            }
        }
        StringBuilder result=new StringBuilder();
        int i=S1.length();
        int j=S2.length();
        while(i>0 && j>0){
            if(S1.charAt(i-1)==S2.charAt(j-1)){
                result.insert(0,S1.charAt(i-1));
                i--;
                j--;
            }else if(tab[i-1][j]>tab[i][j-1]){
                i--;
            }else{
                j--;
            }
        }
        return result.toString();
    }
    public static void main(String args[]) {

        String S1;
        String S2;

        FileInputStream input;
        BufferedReader reader;
        boolean optionN=false;
        boolean optionP=false;

        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-n")) {
                optionN=true;
            }else if (args[i].equals("-p")) {
                optionP=true;
            }else if (args[i].equals("-a")) {
                optionN=true;
                optionP=true;
            }
        }
        for (int j = 1; j < args.length; j++) {
            if (args[j].equals("-n") || args[j].equals("-p") || args[j].equals("-a")) {
                continue;
            }
            try {
                // Ouverture du fichier passé en argument
                input = new FileInputStream(args[j]);
                reader = new BufferedReader(new InputStreamReader(input));

                // Lecture de S1
                S1 = reader.readLine();
                // Lecture S2
                S2 = reader.readLine();

                
                if(optionN && optionP) {
                    long startTime = System.nanoTime();
                    String result = PLSSC(S1, S2);
                    long endTime = System.nanoTime();

                    long startTime_DP = System.nanoTime();
                    String result_PD = PLSSC_PD(S1, S2);
                    long endTime_DP = System.nanoTime();

                    System.out.println("PLSSC_n: " + result);

                    // Impression de la longueur de S1 et de S2 et du temps d'exécution
                    System.out.println("Time_n: " + S1.length() + "\t" + S2.length() + "\t" + ((endTime - startTime)/1.0E9));


                    System.out.println("PLSSC_p: " ls+ result_PD);

                    // Impression de la longueur de S1 et de S2 et du temps d'exécution
                    System.out.println("Time_p: " + S1.length() + "\t" + S2.length() + "\t" + ((endTime_DP - startTime_DP)/1.0E9));
                }
                else if(optionN) {//add the n and p and a options
                    long startTime = System.nanoTime();
                    String result = PLSSC(S1, S2);
                    long endTime = System.nanoTime();
                    System.out.println("PLSSC_n: " + result);

                    // Impression de la longueur de S1 et de S2 et du temps d'exécution
                    System.out.println("Time_n: " + S1.length() + "\t" + S2.length() + "\t" + ((endTime - startTime)/1.0E9));
                }else if(optionP) {
                    long startTime = System.nanoTime();
                    String result_PD = PLSSC_PD(S1, S2);
                    long endTime = System.nanoTime();
                    System.out.println("PLSSC_p: " + result_PD);

                    // Impression de la longueur de S1 et de S2 et du temps d'exécution
                    System.out.println("Time_p: " + S1.length() + "\t" + S2.length() + "\t" + ((endTime - startTime)/1.0E9));
                } else if(!optionN && !optionP){
                    System.out.println("Veuillez spécifier une option : -n pour la méthode naïve, -p pour la programmation dynamique, -a pour les deux.");
                }else{
                    System.out.println("Option invalide. Utilisez -n pour la méthode naïve, -p pour la programmation dynamique, ou -a pour les deux.");
                }
                // date de fin pour le calcul du temps écoulé



            } catch (FileNotFoundException e) {
                System.err.println("Erreur lors de l'ouverture du fichier " + args[j]);
            } catch (IOException e) {
                System.err.println("Erreur de lecture dans le fichier");
            }
        }
    }
}



