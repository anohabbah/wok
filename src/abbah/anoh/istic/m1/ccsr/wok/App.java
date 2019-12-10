package abbah.anoh.istic.m1.ccsr.wok;

import abbah.anoh.istic.m1.ccsr.wok.api.Restaurant;
import abbah.anoh.istic.m1.ccsr.wok.core.Client;
import abbah.anoh.istic.m1.ccsr.wok.core.Cook;
import abbah.anoh.istic.m1.ccsr.wok.core.Employee;
import abbah.anoh.istic.m1.ccsr.wok.core.Wok;
import abbah.anoh.istic.m1.ccsr.wok.utils.Utils;

import java.util.HashMap;
import java.util.Map;

public class App {
    public static void main(String[] args) {
        Restaurant wok = new Wok();
        abbah.anoh.istic.m1.ccsr.wok.core.Employee boy = new Employee(wok);
        abbah.anoh.istic.m1.ccsr.wok.core.Cook chef = new Cook(wok);

//        int nbClients = 5;
        int nbClients = Utils.randomInt(50, 100);
        Map<Integer, Client> clients = new HashMap<>();
        for (int i = 0; i < nbClients; ++i) {
            clients.put(i, new Client(wok));
        }

        boy.start();
        chef.start();
        clients.values().forEach(client -> new Thread(client).start());
    }
}
