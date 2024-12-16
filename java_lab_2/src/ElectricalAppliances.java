import java.util.*;

public class ElectricalAppliances {
    public static void main(String[] args) {
        ElectricalAppliance Fridge = new Fridge("Samsung Family Hub", 400f, 0.5f, false);
        ElectricalAppliance WashingMachine = new WashingMachine("WashingMachine kitchen", 300f, 0.1f, false);

        ElectricalAppliance.SearchByEL(0.2f, 0.6f);
        ElectricalAppliance.SortByPower(); //не видає результату за відсутності увімкнених пристроїв

        ElectricalAppliance Computer = new Computer("Desktop PC", 200f, 0.2f, true);

        Fridge.turnOff();
        System.out.println(" ");

        Fridge.turnOn();
        Computer.turnOn();
        System.out.println(" ");

        System.out.println("SORT BY DESCENDING POWER");
        ElectricalAppliance.SortByPower();
        System.out.println(" ");

        Computer.turnOn();
        WashingMachine.turnOn();
        System.out.println(" ");

        System.out.println("SORT BY DESCENDING POWER");
        ElectricalAppliance.SortByPower();
        System.out.println(" ");

        System.out.println("SEARCH IN THE RANGE 0.2mG - 0.6mG");
        ElectricalAppliance.SearchByEL(0.2f, 0.6f);
        System.out.println(" ");

        Fridge.turnOff();
        Computer.turnOff();
        WashingMachine.turnOff();
    }

}

abstract class ElectricalAppliance {
    protected String name; //назва девайсу
    protected Float power; //пожуність у Вт
    protected Float ElectromagneticRadiation; //Електромагнітне випромінювання (міліГаус)
    protected Boolean isOn; //стан девайсу
    protected static final HashMap<String, Float> ActiveDevicesByPower = new HashMap<>(); //список активних девайсів за потужністю
    protected static final HashMap<String, Float> ActiveDevicesByEL = new HashMap<>(); //список активних девайсів за електромагнітним випромінюванням

    public ElectricalAppliance(String name, Float power, Float electromagnetic_radiation, Boolean isOn) {
        this.name = name;
        this.power = power;
        this.ElectromagneticRadiation = electromagnetic_radiation;
        this.isOn = isOn;

        if (isOn) {
            addToActiveLists();
        }
    }

    private void addToActiveLists() {
        ActiveDevicesByPower.put(name, power);
        ActiveDevicesByEL.put(name, ElectromagneticRadiation);
    }

    public void turnOn() {
        if (!isOn) {
            System.out.println(name + " is turn on.");
            ActiveDevicesByPower.put(name, power);
            ActiveDevicesByEL.put(name, ElectromagneticRadiation);
            isOn = true;
        } else {
            System.out.println(name + " is already turned on.");
        }


    }

    public void turnOff() {
        if (isOn) {
            System.out.println(name + " is turn off.");
            ActiveDevicesByPower.remove(name);
            ActiveDevicesByEL.remove(name);
            isOn = false;
        }
        else {
            System.out.println(name + " is already turned off.");
        }
    }

    public static void SortByPower() {
        List<Map.Entry<String, Float>> entryList = new ArrayList<>(ActiveDevicesByPower.entrySet());
        entryList.sort(Map.Entry.comparingByValue(Collections.reverseOrder()));
        for (Map.Entry<String, Float> entry : entryList) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + "W");
        }
    }

    public static void SearchByEL(Float from, Float to) {
        boolean found = false;
        for (Map.Entry<String, Float> entry : ActiveDevicesByEL.entrySet()) {
            Float value = entry.getValue();
            if (value >= from && value <= to) {
                System.out.println(entry.getKey() + ": " + value + " mG");
                found = true;
            }
        }
        if (!found) {
            System.out.println("No devices found in the specified range.");
        }
    }
}

class Fridge extends ElectricalAppliance {
    public Fridge(String name, Float power, Float electromagnetic_radiation, Boolean isOn) {
        super(name, power, electromagnetic_radiation, isOn);
    }
}

class Computer extends ElectricalAppliance {
    public Computer(String name, Float power, Float electromagnetic_radiation, Boolean isOn) {
        super(name, power, electromagnetic_radiation, isOn);
    }
}

class WashingMachine extends ElectricalAppliance {
    public WashingMachine(String name, Float power, Float electromagnetic_radiation, Boolean isOn) {
        super(name, power, electromagnetic_radiation, isOn);
    }
}

class Plate extends ElectricalAppliance {
    public Plate(String name, Float power, Float electromagnetic_radiation, Boolean isOn) {
        super(name, power, electromagnetic_radiation, isOn);
    }
}
