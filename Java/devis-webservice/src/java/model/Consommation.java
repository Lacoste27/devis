package model;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Consommation {

    static LocalTime __DAY__ = LocalTime.parse("06:00");
    static LocalTime __NIGHT__ = LocalTime.parse("18:00");
    static LocalTime __BEFORE__MIDNIGHT__ = LocalTime.parse("23:59");
    static LocalTime __MIDNIGHT__ = LocalTime.parse("00:00");

    // --------------------------------------------------------------
    public static HourConsommation[] dayConsumptionPerHoure(List<Appareil> devices) {
        HourConsommation[] consumptionPerHours = new HourConsommation[12];
        for (int i = 0; i < consumptionPerHours.length; i++) {
            consumptionPerHours[i] = new HourConsommation(__DAY__.plusHours(i), __DAY__.plusHours(i + 1), 0);
        }
        // set consumption per hour
        for (HourConsommation consumptionPerHour : consumptionPerHours) {
            for (Appareil device : devices) {
                if (isDayNIn(device, consumptionPerHour.getDebut())) {
                    consumptionPerHour
                            .setConsommation(consumptionPerHour.getConsommation() + (device.getPuissance() * device.getQuantite()));
                }
            }
        }

        for (HourConsommation consumptionPerHour : consumptionPerHours) {
            System.out.println(consumptionPerHour.getDebut() + " "
                    + consumptionPerHour.getFin() + " = "
                    + consumptionPerHour.getConsommation());
        }
        return consumptionPerHours;
    }

    private static boolean isDayNIn(Appareil device, LocalTime time) {
        LocalTime start = device.getHeuredebut(), end = device.getHeurefin();
        if (isNight(start) && isNight(end)) {
            // OK
            if (isBeforeDawn(start) && isAfterDusk(end)) {
                if ((time.isAfter(__DAY__) || time.equals(__DAY__)) && time.isBefore(__NIGHT__)) {
                    return true;
                }
                return false;
            }
            if (isBeforeDawn(start) && isBeforeDawn(end)) {
                // OK
                if (start.isAfter(end)) {
                    if ((time.isAfter(__DAY__) || time.equals(__DAY__)) && time.isBefore(__NIGHT__)) {
                        return true;
                    }
                    return false;
                }
            }
            if (isAfterDusk(start) && isAfterDusk(end)) {
                // OK
                if (start.isAfter(end)) {
                    if ((time.isAfter(__DAY__) || time.equals(__DAY__) && time.isBefore(__NIGHT__))) {
                        return true;
                    }
                    return false;
                }
            }
            return false;
        }
        if (isDay(start) && isDay(end)) {
            if (start.isBefore(end)) {
                // OK
                if ((time.isAfter(start) || time.equals(start)) && time.isBefore(end)) {
                    return true;
                }
                return false;
            }
            if (start.isAfter(end)) {
                // OK
                if (((time.isAfter(__DAY__) || time.equals(__DAY__)) && time.isBefore(end))
                        || (time.isAfter(start) || time.equals(start)) && time.isBefore(__NIGHT__)) {
                    return true;
                }
                return false;
            }
        }
        if (isDay(start) || isDay(end)) {
            // OK
            if (isNight(start) && isDay(end)) {
                if ((time.isAfter(__DAY__) || time.equals(__DAY__)) && time.isBefore(end)) {
                    return true;
                }
                return false;
            }
            // OK
            if (isDay(start) && isNight(end)) {
                if ((time.isAfter(start) || time.equals(start)) && time.isBefore(__NIGHT__)) {
                    return true;
                }
                return false;
            }
        }
        return false;
    }

    public static HourConsommation[] nightConsumptionPerHour(List<Appareil> devices) {
        HourConsommation[] consumptionPerHours = new HourConsommation[12];
        for (int i = 6; i < 12; i++) {
            consumptionPerHours[i] = new HourConsommation(__NIGHT__.plusHours(i - 6), __NIGHT__.plusHours(i - 6 + 1),
                    0);
        }
        for (int i = 0; i < 6; i++) {
            consumptionPerHours[i] = new HourConsommation(__MIDNIGHT__.plusHours(i), __MIDNIGHT__.plusHours(i + 1),
                    0);
        }
        for (HourConsommation consumptionPerHour : consumptionPerHours) {
            for (Appareil device : devices) {
                if (isNightNIn(device, consumptionPerHour.getDebut())) {
                    consumptionPerHour.setConsommation(consumptionPerHour.getConsommation() + (device.getPuissance() * device.getQuantite()));
                }
            }
        }

        for (HourConsommation consumptionPerHour : consumptionPerHours) {
            System.out.println(consumptionPerHour.getDebut() + " "
                    + consumptionPerHour.getFin() + " = "
                    + consumptionPerHour.getConsommation());
        }
        return consumptionPerHours;
    }

    public static List<ConsommationHeure> nightConsumptionPerHours(List<Appareil> devices) {
        List<HourConsommation> consumptionPerHours = new ArrayList<>();
        for (int i = 6; i < 12; i++) {
            consumptionPerHours.add(new HourConsommation(__NIGHT__.plusHours(i - 6), __NIGHT__.plusHours(i - 6 + 1), 0));
        }
        for (int i = 0; i < 6; i++) {
            consumptionPerHours.add(new HourConsommation(__MIDNIGHT__.plusHours(i), __MIDNIGHT__.plusHours(i + 1), 0));
        }
        
        for (HourConsommation consumptionPerHour : consumptionPerHours) {
            for (Appareil device : devices) {
                if (isNightNIn(device, consumptionPerHour.getDebut())) {
                    consumptionPerHour.setConsommation(consumptionPerHour.getConsommation() + (device.getPuissance() * device.getQuantite()));
                }
            }
        }

        List<ConsommationHeure> retour = new ArrayList<>();

        for (HourConsommation consumptionPerHour : consumptionPerHours) {
            retour.add(new ConsommationHeure(consumptionPerHour.getDebut().toString(), consumptionPerHour.getFin().toString(), consumptionPerHour.getConsommation()));
            System.out.println(consumptionPerHour.getDebut() + " "
                    + consumptionPerHour.getFin() + " = "
                    + consumptionPerHour.getConsommation());
        }
        return retour;
    }

    public static List<ConsommationHeure> nightBatteryConsumptionPerHours(int puissance, List<Appareil> devices) {
        List<ConsommationHeure> retour = new ArrayList<>();
        List<ConsommationHeure> consommationlist = Consommation.nightConsumptionPerHours(devices);
        int lastindex = puissance;  
        for (ConsommationHeure consommationHeure : consommationlist) {
            retour.add(new ConsommationHeure(consommationHeure.getDebut(), consommationHeure.getFin(), (consommationHeure.getConsommation() == 0) ? lastindex : lastindex - consommationHeure.getConsommation()));
            lastindex = lastindex - consommationHeure.getConsommation();
        }
        return retour;
    }

    private static boolean isNightNIn(Appareil device, LocalTime time) {
        LocalTime start = device.getHeuredebut(), end = device.getHeurefin();
        if (isNight(start) && isNight(end)) {
            // OK
            if (isBeforeDawn(start) && isAfterDusk(end)) {
                if (((time.isAfter(start) || time.equals(start)) && time.isBefore(__DAY__))
                        || ((time.isAfter(__NIGHT__) || time.equals(__NIGHT__)) && time.isBefore(end))) {
                    return true;
                }
                return false;
            }
            // OK
            if (isBeforeDawn(end) && isAfterDusk(start)) {
                if (((time.isAfter(start) || time.equals(start)) && time.isBefore(__BEFORE__MIDNIGHT__))
                        || ((time.isAfter(__MIDNIGHT__) || time.equals(__MIDNIGHT__)) && time.isBefore(end))) {
                    return true;
                }
                return false;
            }

            if (isBeforeDawn(start) && isBeforeDawn(end)) {
                // OK
                if (start.isAfter(end)) {
                    if (((time.isAfter(start) || time.equals(start)) && time.isBefore(__DAY__))
                            || ((time.isAfter(__NIGHT__) || time.equals(__NIGHT__)) && time.isBefore(__BEFORE__MIDNIGHT__))
                            || ((time.isAfter(__MIDNIGHT__) || time.equals(__MIDNIGHT__)) && time.isBefore(end))) {
                        return true;
                    }
                    return false;
                }
                // OK
                if (start.isBefore(end)) {
                    if (time.isAfter(start.minusHours(1)) && time.isBefore(end)) {
                        return true;
                    }
                    return false;
                }
            }
            if (isAfterDusk(start) && isAfterDusk(end)) {
                // OK
                if (start.isAfter(end)) {
                    if (((time.isAfter(start) || time.equals(start)) && time.isBefore(__BEFORE__MIDNIGHT__))
                            || ((time.isAfter(__MIDNIGHT__) || time.equals(__MIDNIGHT__)) && time.isBefore(__DAY__))
                            || ((time.isAfter(__NIGHT__) || time.equals(__NIGHT__)) && time.isBefore(end))) {
                        return true;
                    }
                    return false;
                }
                // OK
                if (start.isBefore(end)) {
                    if ((time.isAfter(start) || time.equals(start)) && time.isBefore(end)) {
                        return true;
                    }
                    return false;
                }
            }
            return false;
        }
        if (isDay(start) && isDay(end)) {
            // OK
            if (start.isAfter(end)) {
                if (((time.isAfter(__NIGHT__) || time.equals(__NIGHT__)) && time.isBefore(__BEFORE__MIDNIGHT__))
                        || ((time.isAfter(__MIDNIGHT__) || time.equals(__MIDNIGHT__)) && time.isBefore(__DAY__))) {
                    return true;
                }
            }
            return false;
        }
        if (isDay(start) || isDay(end)) {
            if (isNight(start) && isDay(end)) {
                // OK
                if (isBeforeDawn(start)) {
                    if ((time.isAfter(start) || time.equals(start)) && time.isBefore(__DAY__)) {
                        return true;
                    }
                    return false;
                }
                // OK
                if (isAfterDusk(start)) {
                    if (((time.isAfter(start) || time.equals(start)) && time.isBefore(__BEFORE__MIDNIGHT__))
                            || ((time.isAfter(__MIDNIGHT__) || time.equals(__MIDNIGHT__)) && time.isBefore(__DAY__))) {
                        return true;
                    }
                    return false;
                }
            }
            if (isDay(start) && isNight(end)) {
                // NON TESTE
                if (isBeforeDawn(end)) {
                    if (((time.isAfter(__NIGHT__) || time.equals(__NIGHT__)) && time.isBefore(__BEFORE__MIDNIGHT__))
                            || ((time.isAfter(__MIDNIGHT__) || time.equals(__MIDNIGHT__)) && time.isBefore(end))) {
                        return true;
                    }
                    return false;
                }
                // NON TESTE
                if (isAfterDusk(end)) {
                    if (((time.isAfter(__NIGHT__) || time.equals(__NIGHT__)) && time.isBefore(end))) {
                        return true;
                    }
                    return false;
                }
            }
        }
        return false;
    }

    public static boolean isBeforeDawn(LocalTime time) {
        if ((time.isAfter(__MIDNIGHT__) || time.equals(__MIDNIGHT__))
                && time.isBefore(__DAY__)) {
            return true;
        }
        return false;
    }

    public static boolean isAfterDusk(LocalTime time) {
        if ((time.isAfter(__NIGHT__) || time.equals(__NIGHT__))
                && time.isBefore(__BEFORE__MIDNIGHT__)) {
            return true;
        }
        return false;
    }

    public static boolean isDay(LocalTime time) {
        if ((time.isAfter(__DAY__) || time.equals(__DAY__)) && time.isBefore(__NIGHT__)) {
            return true;
        }
        return false;
    }

    public static boolean isNight(LocalTime time) {
        if (((time.isAfter(__MIDNIGHT__) || time.equals(__MIDNIGHT__)) && time.isBefore(__DAY__))
                || ((time.isAfter(__NIGHT__) || time.equals(__NIGHT__)) && time.isBefore(__BEFORE__MIDNIGHT__))) {
            return true;
        }
        return false;
    }

    // --------------------------------------------------------------
    public static float highestConsumption(HourConsommation[] consumptionPerHours) {
        HourConsommation max = consumptionPerHours[0];
        for (HourConsommation consumptionPerHour : consumptionPerHours) {
            if (max.getConsommation() < consumptionPerHour.getConsommation()) {
                max = consumptionPerHour;
            }
        }
        return (float) max.getConsommation();
    }

    public int[] getConsommationHour(List<Appareil> list) {
        float day = Consommation.highestConsumption(Consommation.dayConsumptionPerHoure(list));
        float night = Consommation.highestConsumption(Consommation.nightConsumptionPerHour(list)) * 4;
        int[] retour = new int[2];
        retour[0] = (int) day;
        retour[1] = (int) night;
        return retour;
    }
}
