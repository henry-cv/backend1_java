import main.com.flota.dao.impl.AvionDAOH2;
import main.com.flota.model.Avion;
import main.com.flota.service.AvionService;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Avion avion1 = new Avion(1L,"fonda", "cx3", "abc-001", "2020/08/25");
        Avion avion2 = new Avion(2L,"acme", "looney", "acme001","2010/03/03");
        AvionService avionService = new AvionService();
        avionService.setAvionDao(new AvionDAOH2());
        avionService.registrarAvion(avion1);
        avionService.registrarAvion(avion2);
    }
}