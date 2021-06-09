package mc;

import java.io.BufferedReader;
import java.util.Random;
import java.io.InputStreamReader;

public class MC {

    Object[] margem1 = new Object[6];
    Object[] margem2 = new Object[6];
    Barco barco;

    Canibal canibal = new Canibal();
    Missionario missionario = new Missionario();
    boolean goal;

    Random r = new Random();
    BufferedReader bb = new BufferedReader(new InputStreamReader(System.in));

    public MC() {
        initialize();
        goal = goal();
    }

    //    VERIFICAR SE ESTADO GOAL
    public boolean goal() {
        boolean isGoal = false;
        int tC = 0, tM = 0;
        for (int c = 0; c < margem2.length; c++) {
            if (margem2[c] != null) {
                if (margem2[c].toString() == "Canibal") {
                    tC += 1;
                }
                if (margem2[c].toString() == "Missionario") {
                    tM += 1;
                }
            }
        }

        if (tC == 3 && tM == 3) {
            isGoal = true;
        }
        return isGoal;
    }

    public void initialize() {
        margem1[0] = missionario;
        margem1[1] = canibal;
        margem1[2] = missionario;
        margem1[3] = canibal;
        margem1[4] = missionario;
        margem1[5] = canibal;

        margem2[0] = null;
        margem2[1] = null;
        margem2[2] = null;
        margem2[3] = null;
        margem2[4] = null;
        margem2[5] = null;

    }

    public void state() {
        System.out.println("Margem 1:");
        System.out.println("[" + margem1[0] + "]");
        System.out.println("[" + margem1[1] + "]");
        System.out.println("[" + margem1[2] + "]");
        System.out.println("[" + margem1[3] + "]");
        System.out.println("[" + margem1[4] + "]");
        System.out.println("[" + margem1[5] + "]");
        System.out.println("");
        System.out.println("Margem 2:");
        System.out.println("[" + margem2[0] + "]");
        System.out.println("[" + margem2[1] + "]");
        System.out.println("[" + margem2[2] + "]");
        System.out.println("[" + margem2[3] + "]");
        System.out.println("[" + margem2[4] + "]");
        System.out.println("[" + margem2[5] + "]");
        System.out.println("");
    }

    //VERIFICAR SE PODE MOVER MERCENARIOS
    public boolean verifyMovimentMessionary(int quantity) {
        boolean canMove = false;
        int tC = 0, tM = 0;

        for (int i = 0; i < margem1.length; i++) {
            if (margem1[i] == "Missionario") {
                tM += 1;
            }
            if (margem1[i] == "Canibal") {
                tC += 1;
            }
        }
        if ((tM - quantity) >= tC) {
            canMove = true;
        }

        tC = 0;
        tM = 0;
        for (int i = 0; i < margem2.length; i++) {
            if (margem2[i] == "Missionario") {
                tM += 1;
            }

            if (margem2[i] == "Canibal") {
                tC += 1;
            }

            if ((tM + quantity) >= tC) {
                canMove = true;
            } else {
                canMove = false;
            }
        }
        return canMove;
    }

    //VERIFICAR SE PODE MOVER CANIBAL
    public boolean verifyMovimentCanibal(int quantity) {
        boolean canMove = true;
        int tC = 0, tM = 0;

        for (int i = 0; i < margem2.length; i++) {
            if (margem2[i] == "Missionario") {
                tM += 1;
            }

            if (margem2[i] == "Canibal") {
                tC += 1;
            }

            if (((tC + quantity) > tM) && tM != 0) {
                canMove = false;
            }
        }
        return canMove;
    }

    //VERIFICAR SE PODE DEVOLVER MISSIONARIO
    public boolean verifyBackMessionary(int quantity) {
        boolean canBack = false;
        int tC = 0, tM = 0;

        for (int i = 0; i < margem2.length; i++) {
            if (margem2[i] != null) {
                if ((margem2[i].toString() == "Missionario")) {
                    tM += 1;
                }
                if (margem2[i].toString() == "Canibal") {
                    tC += 1;
                }
            }
            if ((tM - quantity) >= tC) {
                canBack = true;
            }
            tC = 0;
            tM = 0;
            for (int c = 0; c < margem1.length; c++) {
                if (margem1[c] == "Missionario") {
                    tM += 1;
                }
                if (margem1[c] == "Canibal") {
                    tC += 1;
                }
            }
            if ((tM + quantity) >= tC) {
                canBack = true;
            } else {
                canBack = false;
            }
        }
        return canBack;
    }

//VERIFICAR SE PODE DEVOLVER Canibal
    public boolean verifyBackCanibal(int quantity) {
        boolean canBack = true;
        int tC = 0, tM = 0;
        for (int i = 0; i < margem1.length; i++) {
            if (margem1[i] != null) {
                if (margem1[i].toString() == "Missionario") {
                    tM += 1;
                }
                if (margem1[i].toString() == "Canibal") {
                    tC += 1;
                }

            } else {
            }
        }
        if ((tC + quantity) > tM) {
            canBack = false;
        }
        return canBack;
    }

    //VERIFICAR SE PODE MOVER OS DOIS
    public boolean verifyMoveBoth() {
        boolean canMove = true;
        int tC = 0, tM = 0;

        for (int c = 0; c < margem2.length; c++) {
            if (margem2[c] == "Missionario") {
                tM += 1;
            } else if (margem2[c] == "Canibal") {
                tC += 1;
            }
        }

        if ((tC + 1) > (tM + 1)) {
            canMove = false;
        } else {
            for (int i = 0; i < margem2.length; i++) {
                if (margem2[i] == null) {
                    margem2[i] = missionario;
                    break;
                }
            }
            for (int i = 0; i < margem2.length; i++) {
                if (margem2[i] == null) {
                    margem2[i] = canibal;
                    break;
                }
            }
        }
        return canMove;
    }

    //VERIFICAR SE PODE DEVOLVER OS DOIS
    public boolean verifyBackBoth() {
        boolean canMove = true;
        int tC = 0, tM = 0;

        for (int c = 0; c < margem1.length; c++) {
            if (margem1[c] == "Missionario") {
                tM += 1;
            } else if (margem1[c] == "Canibal") {
                tC += 1;
            }
        }

        if ((tC + 1) > (tM + 1)) {
            canMove = false;
        } else {
            for (int i = 0; i < margem1.length; i++) {
                if (margem1[i] == null) {
                    margem1[i] = missionario;
                    break;
                }
            }
            for (int i = 0; i < margem1.length; i++) {
                if (margem1[i] == null) {
                    margem1[i] = canibal;
                    break;
                }
            }
        }

        return canMove;
    }

    //REMOVER Missionario DA MARGEM1
    public void removeMessionary(int quant) {
        int times = 0;
        for (int a = 0; a < margem1.length; a++) {
            if (margem1[a] != null) {
                if (margem1[a].toString() == "Missionario") {
                    margem1[a] = null;
                    times += 1;

                    if (times == quant) {
                        break;
                    }

                }
            }
        }
    }

    //REMOVER Canibal DA MARGEM1
    public void removeCanibal(int quant) {
        int times = 0;
        for (int a = 0; a < margem1.length; a++) {
            if (margem1[a] != null) {
                if (margem1[a].toString() == "Canibal") {
                    margem1[a] = null;
                    times += 1;
                }
                if (times == quant) {
                    break;
                }
            }
        }
    }

    //REMOVER Missionario DA MARGEM2
    public void removeMessionary2(int quant) {
        int times = 0;
        for (int a = 0; a < margem2.length; a++) {
            if (margem2[a] != null) {
                if (margem2[a] == "Missionario") {
                    margem2[a] = null;
                    times += 1;
                }
                if (times == quant) {
                    break;
                }
            }
        }
    }

    //REMOVER Canibal DA MARGEM2
    public void removeCanibal2(int quant) {
        int times = 0;
        for (int a = 0; a < margem1.length; a++) {
            if (margem2[a] == "Canibal") {
                margem2[a] = null;
                times += 1;
            }
            if (times == quant) {
                break;
            }
        }
    }

    //REMOVER AMBOS DA MARGEM1
    public void removeBoth1() {
        for (int a = 0; a < margem1.length; a++) {
            if (margem1[a] == "Missionario") {
                margem1[a] = null;
                break;
            }
        }

        for (int a = 0; a < margem1.length; a++) {
            if (margem1[a] == "Canibal") {
                margem1[a] = null;
                break;
            }
        }
    }

    //REMOVER AMBOS DA MARGEM2
    public void removeBoth2() {
        for (int a = 0; a < margem2.length; a++) {
            if (margem2[a] == "Missionario") {
                margem2[a] = null;
                break;
            }
        }

        for (int a = 0; a < margem2.length; a++) {
            if (margem2[a] == "Canibal") {
                margem2[a] = null;
                break;
            }
        }
    }

    public void moverUmMissionario() {
        if (verifyMovimentMessionary(1)) {
            for (int c = 0; c < margem2.length; c++) {
                if (margem2[c] == null) {
                    margem2[c] = missionario;
                    break;
                }
            }
            //Remover da antiga margem
            removeMessionary(1);
            System.out.println("Devolveu um missionario");
            state();
            goal = goal();
        }
    }

    public void moverDoisMissionarios() {
        if (verifyMovimentMessionary(2)) {
            for (int c = 0; c < margem2.length; c++) {
                if (margem2[c] == null) {
                    margem2[c] = missionario;
                    break;
                }
            }

            for (int c = 0; c < margem2.length; c++) {
                if (margem2[c] == null) {
                    margem2[c] = missionario;
                    break;
                }
            }
            removeMessionary(2);
            System.out.println("Moveu dois missionarios");
            state();
            goal = goal();
        }
    }

    public void moverUmCanibal() {
        if (verifyMovimentCanibal(1)) {
            for (int c = 0; c < margem2.length; c++) {
                if (margem2[c] == null) {
                    margem2[c] = canibal;
                    break;
                }
            }

            removeCanibal(1);
            System.out.println("Devolveu um canibal");
            state();

        }
        goal = goal();
    }

    public void moverDoisCanibais() {
        if (verifyMovimentMessionary(2)) {
            for (int c = 0; c < margem2.length; c++) {
                if (margem2[c] == null) {
                    margem2[c] = canibal;
                    break;
                }
            }
            for (int c = 0; c < margem2.length; c++) {
                if (margem2[c] == null) {
                    margem2[c] = canibal;
                    break;
                }
            }
            removeCanibal(2);
            System.out.println("Moveu os dois canibais");
            state();

        }
        goal = goal();
    }

    public void devolverUmMissionario() {
        if (verifyBackMessionary(1)) {
            for (int a = 0; a < margem1.length; a++) {
                if (margem1[a] == null) {
                    margem1[a] = missionario;
                    break;
                }
            }

            removeMessionary2(1);
            System.out.println("Devolveu um missionario");
            state();

        }
        goal = goal();
    }

    public void devolverDoisMissionarios() {
        int times = 0;
        if (verifyBackMessionary(2)) {
            for (int a = 0; a < margem1.length; a++) {
                if (margem1[a] == null) {
                    margem1[a] = missionario;
                    times += 1;
                }
                if (times == 2) {
                    break;
                }
            }
            removeMessionary2(2);
            System.out.println("Devolveu dois missionarios");
            state();

        }
        goal = goal();
    }

    public void devolverUmCanibal() {
        if (verifyBackCanibal(1)) {
            for (int a = 0; a < margem1.length; a++) {
                if (margem1[a] == null) {
                    margem1[a] = canibal;
                    break;
                }
            }
            for (int a = 0; a < margem2.length; a++) {
                if (margem2[a] != null) {
                    if (margem2[a].toString() == "Canibal") {
                        margem2[a] = null;
                        break;
                    }
                }
            }
            removeMessionary2(1);
            System.out.println("Devolveu um canibal");
            state();

        }
        goal = goal();
    }

    public void devolverDoisCanibais() {
        if (verifyBackCanibal(2)) {
            int times = 0;
            for (int a = 0; a < margem1.length; a++) {
                if (margem1[a] == null) {
                    margem1[a] = canibal;
                    times += 1;
                    if (times == 2) {
                        break;
                    }

                }
            }
            for (int a = 0; a < margem2.length; a++) {
                if (margem2[a] != null) {
                    if (margem2[a].toString() == "Canibal") {
                        margem2[a] = null;
                        times += 1;
                        if (times == 2) {
                            break;
                        }
                    }
                }
            }

            removeMessionary2(1);
            System.out.println("Devolveu dois canibais");
            state();

        }
        goal = goal();
    }

    public void moverOsDois() {
        if (verifyMoveBoth()) {
            for (int a = 0; a < margem2.length; a++) {
                if (margem2[a] == null) {
                    margem2[a] = missionario;
                    break;
                }
            }
            for (int a = 0; a < margem2.length; a++) {
                if (margem2[a] == null) {
                    margem2[a] = canibal;
                    break;
                }
            }
            for (int a = 0; a < margem1.length; a++) {
                if (margem1[a] != null) {
                    if (margem1[a].toString() == "Missionario") {
                        margem1[a] = null;
                        break;
                    }
                }
            }
            for (int a = 0; a < margem1.length; a++) {
                if (margem1[a] != null) {
                    if (margem1[a].toString() == "Canibal") {
                        margem1[a] = null;
                        break;
                    }
                }

            }
            System.out.println("Moveu os dois");
            state();

        }
        goal = goal();
    }

    public void devolverOsDois() {
        if (verifyBackBoth()) {
            for (int a = 0; a < margem1.length; a++) {
                if (margem1[a] == null) {
                    margem1[a] = missionario;
                    break;
                }
            }
            for (int a = 0; a < margem1.length; a++) {
                if (margem1[a] == null) {
                    margem1[a] = canibal;
                    break;
                }
            }
            for (int a = 0; a < margem2.length; a++) {
                if (margem2[a] != null) {
                    if (margem2[a].toString() == "Missionario") {
                        margem2[a] = null;
                        break;
                    }
                }
            }
            for (int a = 0; a < margem2.length; a++) {
                if (margem2[a] != null) {
                    if (margem2[a].toString() == "Canibal") {
                        margem2[a] = null;
                        break;
                    }
                }

            }
            System.out.println("Devolveu os dois");
            state();
            goal = goal();

        }

    }

    public void escolheMovimento() {
        int aleatorio = r.nextInt((10 - 1) + 1) + 1;

        switch (aleatorio) {
            case 1:
                moverUmMissionario();
                break;
            case 2:
                moverDoisMissionarios();
                break;
            case 3:
                moverUmCanibal();
                break;
            case 4:
                moverDoisCanibais();
                break;
            case 5:
                devolverUmMissionario();
                break;
            case 6:
                devolverDoisMissionarios();
                break;
            case 7:
                devolverUmCanibal();
                break;
            case 8:
                devolverDoisCanibais();
                break;
            case 9:
                devolverDoisCanibais();
                break;
            case 10:
                moverOsDois();
                break;
            case 11:
                devolverOsDois();
                break;

        }
    }

    public void inicio() {
        while (!goal) {
            escolheMovimento();
        }
        System.out.println("");
        System.out.println("GOAL!!!!");
        state();
    }

}
