package lesson3.decorator;

interface Notifier {
    void send(String message);
}

class EmailNotifier implements Notifier {
    @Override
    public void send(String message) {
        System.out.println("Отправка email: " + message);
    }
}

abstract class NotifierDecorator implements Notifier {
    private Notifier wrappee;

    public NotifierDecorator(Notifier wrappee) {
        this.wrappee = wrappee;
    }

    @Override
    public void send(String message) {
        wrappee.send(message);
    }
}

class SMSNotifier extends NotifierDecorator {
    public SMSNotifier(Notifier wrappee) {
        super(wrappee);
    }

    @Override
    public void send(String message) {
        super.send(message);
        System.out.println("Отправка SMS: " + message);
    }
}

class TelegramNotifier extends NotifierDecorator {
    public TelegramNotifier(Notifier wrappee) {
        super(wrappee);
    }

    @Override
    public void send(String message) {
        super.send(message);
        System.out.println("Отправка Telegram сообщения: " + message);
    }
}

public class DecoratorExample {
    public static void main(String[] args) {
        Notifier notifier = new EmailNotifier();
        notifier = new SMSNotifier(notifier);
        notifier = new TelegramNotifier(notifier);

        notifier.send("Новое уведомление");
    }
}
