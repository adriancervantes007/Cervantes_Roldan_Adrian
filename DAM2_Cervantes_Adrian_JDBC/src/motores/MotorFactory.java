package motores;

public final class MotorFactory {
    public static final int POSTGRE = 1;

    private MotorFactory() {
        }
        public static MotorSQL create(int motor) {
            if (motor == POSTGRE) {
                return new PostgreMotorSQL();
            }
            throw new IllegalArgumentException("Motor SQL no soportado: " + motor);
        }
    }
