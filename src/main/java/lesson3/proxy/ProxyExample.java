package lesson3.proxy;

interface Server {
    void read();
    void write(String role);
}

class RealServer implements Server {
    RealServer() throws InterruptedException {
        System.out.println("RealServer: созание реального сервера...");
        Thread.sleep(2000);
        System.out.println("RealServer: реальный сервер готов...");
    }

    @Override
    public void read() {
        System.out.println("RealServer: чтение данных из реального сервера...");
    }

    @Override
    public void write(String role) {
        System.out.printf("RealServer: запись данных в реальный сервер пользователем %s...\n", role);
    }
}

class ProxyServer implements Server {
    RealServer realServer;

    @Override
    public void read() {
        if (realServer == null) {
            System.out.println("Прокси: при первом вызове создаем экземпляр реального сервера");
            try {
                realServer = new RealServer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Прокси: используем уже созданный экземпляр реального сервера");
        }
        realServer.read();
    }

    @Override
    public void write(String role) {
        if (realServer == null) {
            System.out.println("Прокси: ошибка сервер не создан");
        }
        if (role.equals("ADMIN")){
            realServer.write(role);
        } else {
            System.out.printf("Прокси: %s, у вас нет прав на запись\n", role);
        }
    }
}

public class ProxyExample {
    public static void main(String[] args) {
        ProxyServer proxyServer = new ProxyServer();
        System.out.println("\nПервое чтение из прокси:");
        proxyServer.read();
        System.out.println("\nВторое и последующие чтения из прокси:");
        proxyServer.read();
        System.out.println("\nЗапись из прокси с проверкой роли (роль USER)");
        proxyServer.write("USER");
        System.out.println("\nЗапись из прокси с проверкой роли (роль ADMIN)");
        proxyServer.write("ADMIN");
    }
}
