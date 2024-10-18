def suma(a,b):
    return a+b

#Utilizando el parametro args (dudoso)
def resta(numeros):
    resultado = numeros[0]
    for num in numeros[1:]:
        resultado -= num
    return resta(numeros)

resultado = resta([10,5,6,7,6,5])
print(resultado)