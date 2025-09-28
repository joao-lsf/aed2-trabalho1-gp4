import time
from typing import List


def contar_maneiras_de_fazer_troco(moedas: List[int], troco: int) -> int:
    """
    Conta o número de combinações totais de moedas
    
    Argumentos:
        moedas: Lista de valores das moedas disponíveis
        troco: Valor total que as moedas devem somar
    
    Retorno:
        Número inteiro representando quantas combinações diferentes existem
    """
    formas_por_valor = [0] * (troco + 1)
    formas_por_valor[0] = 1  # Caso base: 1 maneira de fazer troco para valor 0
    
    for moeda in moedas:
        for valor in range(moeda, troco + 1):
            formas_por_valor[valor] += formas_por_valor[valor - moeda]
    
    return formas_por_valor[troco]


def listar_combinacoes(moedas: List[int], troco: int) -> List[List[int]]:
    """
    Lista todas as combinações possíveis de moedas que somam o troco
    
    Argumentos:
        moedas: Lista de valores das moedas disponíveis
        troco: Valor total que as moedas devem somar
    
    Returno:
        Lista de combinações, onde cada combinação é uma lista de moedas
    """
    moedas_ordenadas = sorted(moedas)
    
    # combinacoes_possiveis[valor] armazena todas as combinações possíveis para aquele valor
    combinacoes_possiveis: List[List[List[int]]] = [[] for _ in range(troco + 1)]
    combinacoes_possiveis[0].append([])  # Combinação vazia para valor 0
    
    for moeda in moedas_ordenadas:
        for valor in range(moeda, troco + 1):
            # Para cada combinação que soma (valor - moeda), adicionamos a moeda atual
            for combinacao in combinacoes_possiveis[valor - moeda]:
                nova_combinacao = combinacao + [moeda]
                combinacoes_possiveis[valor].append(nova_combinacao)
    
    return combinacoes_possiveis[troco]

# Input de dados
moedas = [1, 5]
troco = 6

print("=== Problema: Combinações de Moedas usando Programação Dinâmica ===")
print(f"Moedas disponíveis: {moedas}")
print(f"troco: {troco}")

# 1. Contagem das combinações e tempo de execução da contagem
inicio = time.time()
total = contar_maneiras_de_fazer_troco(moedas, troco)
duracao_contagem_ms = (time.time() - inicio) * 1000
print(f"Total de combinações: {total}\n")

# 2. Listagem de todas as combinações e tempo de execução da listagem
print("=== Lista de Todas as Combinações ===")
inicio = time.time()
combinacoes = listar_combinacoes(moedas, troco)
duracao_listagem_ms = (time.time() - inicio) * 1000

# Formatação da saída
combinacoes_ordenadas = sorted(combinacoes, key=lambda x: (len(x), x))

for i, combinacao in enumerate(combinacoes_ordenadas, 1):
    combinacao_formatada = f"({'+'.join(map(str, combinacao))})"
    print(f"{i:2d}. {combinacao_formatada} = {sum(combinacao)}")

# impressão do tempo de execução
print(f"\n=== Tempo ===")
print(f"Tempo para listagem: {duracao_listagem_ms:.3f} milissegundos")
print(f"Tempo para contagem: {duracao_contagem_ms:.3f} milissegundos")
print(f"Tempo total: {(duracao_contagem_ms + duracao_listagem_ms):.3f} milissegundos")
print()
