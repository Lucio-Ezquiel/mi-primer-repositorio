class PromedioCuadrante:
    def __init__(self, posicion, promedio):
        self.posicion = posicion
        self.promedio = promedio

    def __repr__(self):
        return f"{self.posicion}: {self.promedio:.2f}"

def obtener_tamano_matriz():
    while True:
        try:
            tamano = int(input("Ingrese el tamaño de la matriz (par y mayor o igual a 4): "))
            if tamano >= 4 and tamano % 2 == 0:
                return tamano
            else:
                print("El tamaño debe ser un número par mayor o igual a 4.")
        except ValueError:
            print("Por favor, ingrese un número válido.")

def cargar_matriz(tamano):
    matriz = []
    print(f"Ingrese los valores para una matriz de {tamano}x{tamano}:")
    for i in range(tamano):
        fila = []
        for j in range(tamano):
            valor = int(input(f"Ingrese el valor para la posición ({i+1},{j+1}): "))
            fila.append(valor)
        matriz.append(fila)
    return matriz

def calcular_promedio_cuadrantes(matriz, tamano):
    #Al hacer esto omitimos la parte decimal y redondeamos.
    mitad = tamano // 2
    cuadrantes = []
    
    superior_izquierdo = [matriz[i][j] for i in range(mitad) for j in range(mitad)]
    promedio_superior_izquierdo = sum(superior_izquierdo) / len(superior_izquierdo)
    cuadrantes.append(("Promedio Superior Izquierdo", promedio_superior_izquierdo))
    
    superior_derecho = [matriz[i][j] for i in range(mitad) for j in range(mitad, tamano)]
    promedio_superior_derecho = sum(superior_derecho) / len(superior_derecho)
    cuadrantes.append(("Promedio Superior Derecho", promedio_superior_derecho))
    
    inferior_izquierdo = [matriz[i][j] for i in range(mitad, tamano) for j in range(mitad)]
    promedio_inferior_izquierdo = sum(inferior_izquierdo) / len(inferior_izquierdo)
    cuadrantes.append(("Promedio Inferior Izquierdo", promedio_inferior_izquierdo))
    
    inferior_derecho = [matriz[i][j] for i in range(mitad, tamano) for j in range(mitad, tamano)]
    promedio_inferior_derecho = sum(inferior_derecho) / len(inferior_derecho)
    cuadrantes.append(("Promedio Inferior Derecho", promedio_inferior_derecho))
    
    centro = [matriz[i][j] for i in range(mitad-1, mitad+1) for j in range(mitad-1, mitad+1)]
    promedio_centro = sum(centro) / len(centro)
    cuadrantes.append(("Promedio Centro", promedio_centro))
    
    return cuadrantes

# 4. Crear objetos PromedioCuadrante y agregarlos a la lista
def crear_objetos_promedio(cuadrantes):
    lista_promedios = [PromedioCuadrante(pos, promedio) for pos, promedio in cuadrantes]
    return lista_promedios

# 5. Ordenar la lista de objetos de mayor a menor
def ordenar_lista_por_promedio(lista_promedios):
    return sorted(lista_promedios, key=lambda x: x.promedio, reverse=True)

# 6. Calcular el promedio final
def calcular_promedio_final(lista_promedios):
    suma_promedios = sum(obj.promedio for obj in lista_promedios)
    return suma_promedios / len(lista_promedios)

def main():
    tamano = obtener_tamano_matriz()
    matriz = cargar_matriz(tamano)
    
    cuadrantes = calcular_promedio_cuadrantes(matriz, tamano)
    lista_promedios = crear_objetos_promedio(cuadrantes)
    
    # Mostrar los promedios antes de ordenar
    print("\nPromedios antes de ordenar:")
    for promedio in lista_promedios:
        print(promedio)
    
    lista_promedios_ordenada = ordenar_lista_por_promedio(lista_promedios)
    
    # Mostrar los promedios después de ordenar
    print("\nPromedios después de ordenar de mayor a menor:")
    for promedio in lista_promedios_ordenada:
        print(promedio)
    
    # Calcular y mostrar el promedio final
    promedio_final = calcular_promedio_final(lista_promedios_ordenada)
    print(f"\nEl promedio final de los cuadrantes es: {promedio_final:.2f}")

if __name__ == "__main__":
    main()
