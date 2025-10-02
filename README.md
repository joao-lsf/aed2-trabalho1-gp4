# AED2 - Trabalho 1 - Grupo 4

# Algoritmo Recursivo: Multiplicação de Karatsuba

O algoritmo consiste em simplificar a multiplicação de números grandes que levariam muito tempo para multiplicar por algoritmos convencionais.

## Funcionamento

Dados 2 números, por exemplo, a = 3044  e b = 4044, o algoritmo  consiste em realizar os seguintes passos:

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
o algoritmo novamente, de forma recursiva, e multiplicações entre números 
de até 3 dígitos são feitas diretamente. Todos os outros passos gastam O(1).

## Teorema Mestre

Considerando que os passos 1 e 2, e a multiplicação simples da observação 
consomem, na implementação, n passos. Os passos 

Complexidade: 

$$ T(n) = 3T(\frac{n}{2}) + n $$

$$ a = 3; b = 2; f(n) = n $$

$$ \log_2 2 = 1 ; \log_2 4 = 2 $$

$$ \log_2 2 \lt \log_2 3 ; $$

$$ f(n) \lt n^{\log_b a} \rightarrow n \lt n^{\log_2 3} $$

Logo, a solução da recorrência é 

$$ T(n) = \theta (n^{\log_2 3}) $$

## Execução

Nossa implementação foi feita em Java, no arquivo ``karatsuba.java``. Para executar, basta rodar o seguinte comando, do ``OpenJDK 25``

```bash
java karatsuba.java <A> <B>
```

Substituindo ``<A>`` e ``<B>`` pelos números a serem multiplicados.

### Exemplo de execução: 

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

# Algoritmo Dinâmico: Problema do Troco

O Problema do Troco é um desafio clássico da ciência da computação. O objetivo é, dado um conjunto de moedas e um valor de troco, determinar o número total de combinações distintas de moedas que somam exatamente esse valor.

## Implementação

Nossa implementação vai além e resolve duas questões:
1.  *Contagem:* Quantas combinações diferentes existem?
2.  *Listagem:* Quais são todas essas combinações?

Para resolver o problema de forma eficiente, foi utilizada a abordagem da *Programação Dinâmica*. A seguir, detalhamos como cada função do código aplica esta técnica.

### 1. Contagem das Combinações (contar_maneiras_de_fazer_troco)

Esta função calcula apenas o número total de combinações.
* *Lógica:* A lista formas_por_valor é usada como o array da programação dinâmica. Com tamanho V + 1 (onde V é o troco), a posição formas_por_valor[i] armazena o número de maneiras de formar o valor i.
* *Execução:* O algoritmo itera por cada moeda e, para cada uma, atualiza a lista formas_por_valor, somando as novas combinações possíveis. Ao final, formas_por_valor[troco] contém a resposta total.
* *Performance:* Esta abordagem é extremamente rápida e eficiente em memória, pois armazena apenas contagens.

### 2. Listagem de Todas as Combinações (listar_combinacoes)

Esta função gera a lista completa de todas as combinações possíveis.
* *Lógica:* A estrutura de programação dinâmica aqui é a lista combinacoes_possiveis. Em vez de armazenar contagens, combinacoes_possiveis[i] armazena uma lista contendo todas as combinações que somam o valor i.
* *Execução:* Para cada moeda, o algoritmo constrói novas combinações adicionando a moeda atual às combinações já calculadas para valores menores.
* *Performance:* Embora poderosa, esta função consome significativamente mais tempo e memória, pois precisa criar e armazenar todas as listas de combinações individuais.


## Complexidade

A complexidade varia significativamente entre as duas funções implementadas.

* *Função de Contagem (contar_maneiras_de_fazer_troco)*
    * *Complexidade de Tempo:* $O(V \times M)$
    * *Complexidade de Espaço:* $O(V)$

* *Função de Listagem (listar_combinacoes)*
    * *Complexidade de Tempo:* Pior que $O(V \times M)$. O desempenho é diretamente impactado pelo número total de combinações (K), pois envolve a criação e cópia de múltiplas listas.
    * *Complexidade de Espaço:* Depende do número e do tamanho das combinações. É significativamente maior que $O(V)$, pois precisa armazenar todas as K combinações em memória.

Onde V é o valor do troco e M é o número de moedas disponíveis.


## Implementação

O algoritmo foi implementado na linguagem *Python*.

* *Arquivo Principal:* ``Troco.py``
* *Bibliotecas Utilizadas:*
    * time: Para medir e comparar o tempo de execução das duas funções.
    * typing: Para adicionar dicas de tipo e melhorar a legibilidade do código.


## Como Executar

O script foi projetado para ser executado diretamente, sem a necessidade de argumentos de linha de comando. Os valores de entrada são definidos no próprio código.

1.  *Configure a Entrada:*
    Abra o arquivo Python e altere os valores das variáveis moedas e troco conforme desejado.
        
    ```python
    moedas = [1, 5, 10, 25, 50, 100]
    troco = 26
    ```
    

3.  *Execute o script:*
    Abra um terminal na pasta do projeto e execute o seguinte comando:

    ```
    python Troco.py
    ```
    

### Saída de Exemplo

A execução do script com os valores padrão ( ``troco = 26``, ``moedas = [1, 5, 10, 25, 50, 100]`` ) produzirá a seguinte saída:

```
=== Problema: Combinações de Moedas usando Programação Dinâmica ===
Moedas disponíveis: [1, 5, 10, 25, 50, 100]
troco: 26
Total de combinações: 13

=== Lista de Todas as Combinações ===
 1. (1+25) = 26
 2. (1+5+10+10) = 26
 3. (1+5+5+5+10) = 26
 4. (1+5+5+5+5+5) = 26
 5. (1+1+5+5+5+5+5+5+5+5+5+5) = 26
 6. (1+1+1+1+1+1) = 26
 7. (1+1+1+1+1+1+1+1+1+1+1+1+1+1+1+1+1+1+1+1+1+1+1+1+1+1) = 26
 8. (5+5+5+5+5+1) = 26
 9. (5+5+5+1+1+1+1+1+1) = 26
10. (5+5+1+1+1+1+1+1+1+1+1+1+1+1+1+1+1) = 26
11. (5+1+1+1+1+1+1+1+1+1+1+1+1+1+1+1+1+1+1+1+1+1) = 26
12. (10+5+5+5+1) = 26
13. (10+10+5+1) = 26

=== Tempo ===
Tempo para listagem: 0.123 milissegundos
Tempo para contagem: 0.045 milissegundos
Tempo total: 0.168 milissegundos
```

(Nota: Os tempos de execução podem variar ligeiramente dependendo da máquina.)


# Autores

* *João Luiz Schiavini Filho*
* *Felippe Carballo Leal*
* *Matheus Gonçalves do Nascimento Bandeira*
