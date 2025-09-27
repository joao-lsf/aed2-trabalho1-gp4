# AED2 - Trabalho 1 - Grupo 3

## Alunos

- João Luiz Schiavini Filho
- Felippe Carballo Leal
- Matheus Gonçalves do Nascimento Bandeira

## Algoritmo Recursivo: Multiplicação de Karatsuba

O algoritmo consiste em simplificar a multiplicação de números grandes.

Nossa implementação foi feita em Java, no arquivo ``karatsuba.java``. Para executar, basta rodar o seguinte comando, do ``OpenJDK 25``

```bash
java karatsuba.java <A> <B>
```

Substituindo ``<A>`` e ``<B>`` pelos números.

Funcionamento: dados 2 números $ a = 3044 $ e $ b = 4044 $, o algoritmo  consiste em realizar os seguintes passos:

```

1. Dividir os números ao meio pelos dígitos (arredondando pra cima):

a => 3044 -> 30 e 44 (a1 e a2)
b => 4044 -> 40 e 44 (b1 e b2)

2. Guarde o número de dígitos dos novos números

m = 2

3. Multiplicar os novos números de acordo com sua posição mútua original:

c = a1 * b1
d = a2 * b2

1200 = 30 * 40
1936 = 44 * 44

4. Somar os novos números entre si e multiplicar os resultados:

a12 = a1 + a2
b12 = b1 + b2

74 = 30 + 44
84 = 40 + 44

f = a12 * b12

6216 = 74 * 84

5. Soma final com o resultado, seguindo a fórmula:

ab =    c          * 10^(m2) 
     + (f - c - d) * 10^m
     +  d
      
12309936 =    1200                * 10000
           + (6216 - 1200 - 1936) * 100
           +  1936
```

Observação: Na implementação, as multiplicações dos passos 3 e 4 chamam 
o algoritmo de forma recursiva, e multiplicações entre números de até 3 
dígitos são feitas diretamente.

### Teorema Mestre

Considerando que os passos 1 e 2, e a multiplicação simples da observação 
consomem, na implementação, n passos. Os passos 

Complexidade: $ T(n) = 3T(n/2) + n $

$$ T(n) = 3T(\frac{n}{2}) + n $$

$$ a = 3; b = 2; f(n) = n $$

$$ f(n) \lt n^{log_b a} \rightarrow n \lt n^{log_2 3} $$

Logo, a solução da recorrência é $ T(n) = \theta (n^{log_2 3}) $

### Execução

```
Karatsuba $ java karatsuba.java 3524365 55453655

Multiplicação por Karatsuba: ( a x b )
a = 3524365
b = 55453655

(a x b) : 1954207042784075

Tempo de execução do algoritmo (em nanossegundos)
Início :  1759002372985724170
Fim :     1759002372985987595



Karatsuba $ java karatsuba.java 3044 4044

Multiplicação por Karatsuba: ( a x b )
a = 3044
b = 4044

(a x b) : 12309936

Tempo de execução do algoritmo (em nanossegundos)
Início :  1759002518341913614
Fim :     1759002518342237482
```



