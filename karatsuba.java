import java.util.regex.Pattern;
import java.time.Instant;

public class karatsuba {

    public static String karatsuba(long a, long b){
        /**
         * Foi escolhido trabalhar principalmente com String
         * devido ao início do código que pede a quantidade de dígitos
         * e divide o número. A divisão e conquista fica mais fácil
         * (de explicar) com String do que com Long.
         */
        return karatsuba(Long.toString(a), Long.toString(b));
    }


    /**
     * Calcula a multiplicação de a por b pelo algoritmo de Karatsuba
     * @param a
     * @param b
     * @return a * b
     */
    public static String karatsuba(String a, String b){
        // Se ambos os números tiverem até 3 dígitos, retornar a soma
        if (a.length() < 4 || b.length() < 4){
            return Long.toString( Long.parseLong(a) * Long.parseLong(b) );
        }

        // Obtém o número com a maior quantidade de dígitos
        int m = Math.max(a.length(), b.length());
        // Obtém a metade de m arredondando pra cima
        m = Math.ceilDiv(m, 2);

        // Divisões (de DC)
        // Divisão em a1 e a2
        String a1 = a.substring(0, m);
        String a2 = a.substring(m);
        // Divisão em b1 e b2
        String b1 = b.substring(0, m);
        String b2 = b.substring(m);

        // Conquistas

        // Multiplicação a1 x b1
        long c = Long.parseLong(karatsuba(a1, b1));
        // Multiplicação a2 x b2
        long d = Long.parseLong(karatsuba(a2, b2));
            
        // Somas para a próxima multiplicação 
        long a12 = Long.parseLong(a1) + Long.parseLong(a2);
        long b12 = Long.parseLong(b1) + Long.parseLong(b2);

        // Multiplicação ( a1 + a2 ) x ( b1 + b2 )
        long f = Long.parseLong(karatsuba(a12, b12));

        // Karatsuba = c * 10^(m2) + (f - c - d) * 10^m + d
        // c = a1 x b1
        // d = a2 x b2
        // f = ( a1 x a2 ) + ( b1 x b2 )
        long ab = ((Math.powExact(10, m*2) * c) + (Math.powExact(10, m) * (f - c - d)) + d);
        return Long.toString(ab);
    }

    public static void sairComErro(String msg) {
        if (msg != null){
            System.err.println(msg);
        }
        System.out.println("Uso: ");
        System.out.println("java karatsuba.java <numero_a> <numero_b>");
        System.out.println("");
    }
    public static long getTimeNS(){
        Instant now = Instant.now();
        long epochNanos = now.getEpochSecond() * 1_000_000_000L + now.getNano();
        return epochNanos;
    }
    
    public static void main(String[] args) {
        if (args.length == 0){
            sairComErro(null);
        }
        if (args.length < 2){
            sairComErro("Erro: digite ao menos 2 argumentos");
            return;
        }

        String a = args[0];
        String b = args[1];

        // Sanitização de input
        if (a.matches(".*\\D+.*")) {
            sairComErro("Erro: o primeiro número (" + a + ") contém caracteres inválidos");
            return;
        }
        if (b.matches(".*\\D+.*")) {
            sairComErro("Erro: o segundo número (" + b + ") contém caracteres inválidos");
            return;
        }

        // Mostrando os números que serão multiplicados
        System.out.println("\nMultiplicação por Karatsuba: ( a x b )");
        System.out.println("a = " + a);
        System.out.println("b = " + b);

        // Execução e registro dos tempos do algoritmo
        String t0 = Long.toString(getTimeNS());
        System.out.println("\n(a x b) : " + karatsuba(a, b));
        String tf = Long.toString(getTimeNS());

        // Mostrando o tempo de execução
        System.out.println("\nTempo de execução do algoritmo (em nanossegundos)");
        System.out.println("Início :  " + t0);
        System.out.println("Fim :     " + tf + "\n");

        return;
        
    }
    
}