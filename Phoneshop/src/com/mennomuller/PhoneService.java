package com.mennomuller;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class PhoneService {

    private final ArrayList<Phone> phones = new ArrayList<>(List.of(new Phone[]{
            new Phone(1, "Huawei", "P30",
                    """
                            6.47" FHD+ (2340x1080) OLED,
                            Kirin 980 Octa-Core (2x Cortex-A76 2.6GHz+ 2x Cortex-A76 1.92GHz + 4x Cortex-A55 1.8GHz),
                            8GB RAM, 128GB ROM,40+20+8+TOF/32MP,
                            Dual SIM, 4200mAh, Android 9.0 + EMUI 9.1""", new BigDecimal(697)),
            new Phone(2, "Samsung", "Galaxy A52",
                    """
                            64 megapixel camera, 4k videokwaliteit
                            6.5 inch AMOLED scherm
                            128 GB opslaggeheugen (Uitbreidbaar met Micro-sd)
                            Water- en stofbestendig(IP67)""", new BigDecimal(399)),
            new Phone(3, "Apple", "IPhone 11",
                    """
                            Met de dubbele camera schiet je in elke situatie een perfecte foto of video.
                            De krachtige A13-chipset zorgt voor razendsnelle prestaties.
                            Met Face ID hoef je enkel en alleen naar je toestel te kijken om te ontgrendelen.
                            Het toestel heeft een lange accuduur dankzij een energiezuinige processor.""", new BigDecimal(619)),
            new Phone(4, "Google", "Pixel 4a",
                    """
                            12.2 megapixel camera, 4k videokwaliteit
                            5.81 inch OLED scherm 2
                            128 GB opslaggeheugen
                            3140 mAh accucapaciteit""", new BigDecimal(411)),
            new Phone(5, "Xiaomi", "Redmi Note 10 Pro",
                    """
                            108 megapixel camera, 4k videokwaliteit
                            6.67 inch AMOLED scherm
                            128GB opslaggeheugen (Uitbreidbaar met Micro-sd)
                            Water- en stofbestendig(IP53)""", new BigDecimal(298))
    }));

    public Phone getPhone(int id) {
        return phones.stream().filter(Objects::nonNull)
                .filter(p -> p.id() == id)
                .findFirst().orElseThrow(IllegalArgumentException::new);
    }

    public List<Phone> search(String query) {
        return phones.stream().filter(Objects::nonNull).filter(p -> p.description().contains(query)
                || p.brand().contains(query) || p.model().contains(query)).collect(Collectors.toList());
    }

    public int getMaxId() {
        return phones.stream().max(Comparator.comparing(Phone::id))
                .orElseThrow(NoSuchElementException::new).id();
    }

    public ArrayList<Phone> getPhones() {
        return phones;
    }
}
