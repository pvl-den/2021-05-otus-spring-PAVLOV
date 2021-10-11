package ru.otus.homework29.transformation;

import org.springframework.stereotype.Service;
import ru.otus.homework29.domain.Butterfly;
import ru.otus.homework29.domain.Caterpillar;

@Service
public class TransformService {

    public Butterfly hutch(Caterpillar caterpillar) throws Exception {
        System.out.println("Гусеница " + caterpillar.getNumber());
        Thread.sleep(30);
        System.out.println("Превращение Бабочки из гусеницы " + caterpillar.getNumber() + " выполнено");
        return new Butterfly("Новая Бабочка " + caterpillar.getNumber());
    }
}
