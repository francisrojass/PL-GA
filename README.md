# Conjunto de Problemas resueltos con PL y AG

## Problema 1: Planificación de Cultivos

El agricultor posee varios terrenos, cada uno de diferentes tamaños en metros cuadrados. El objetivo es planificar la siembra de diferentes variedades de verduras. Cada variedad requiere una cantidad específica de metros cuadrados y puede ser seleccionada o no para su siembra. Además, algunas variedades pueden ser incompatibles entre sí, lo que significa que no pueden ser sembradas en el mismo terreno. El objetivo es maximizar la cantidad de variedades de verduras sembradas, cumpliendo con las restricciones de espacio y compatibilidad entre ellas.

## Problema 2: Composición de Cesta de Navidad

Se desea componer una cesta de Navidad que incluya diversos productos. De cada producto se conoce su categoría, su precio y su valoración (un entero entre 1 y 5). Determinar la composición de la cesta de forma que:
- entre los productos seleccionados se cubran todas las categorías,
- la media de las valoraciones de todos los productos seleccionados sea mayor o igual a 3,
- el precio total de los productos seleccionados de una misma categoría no supere un presupuesto dado (este presupuesto es común a todas las categorías), y
- se minimice el precio total de la cesta.

## Problema 3: Distribución de Productos

Un distribuidor de productos debe decidir la cantidad de unidades de diferentes productos para enviar a distintos destinos. De cada destino se conoce la demanda mínima de cantidad total de productos a almacenar en ese destino, que debe cumplirse. Además, para cada tipo de producto y cada destino se tiene información sobre el costo de almacenamiento de cada unidad de producto en dicho destino. El objetivo es saber cuántas unidades de cada producto enviar a cada destino, minimizando el costo total de almacenamiento y cumpliendo con las demandas mínimas de productos de cada destino.

## Problema 4: Emparejamiento de Individuos

Dada una lista de personas, es necesario emparejarlas de dos en dos (suponiendo que siempre hay un número par de personas). De cada persona se conoce el conjunto de idiomas que habla, su edad y su nacionalidad. También se conoce la afinidad que existe entre cada par de personas (un entero en el rango de 0 a 5). El objetivo es obtener una solución que maximice la suma total de las afinidades obtenidas con los emparejamientos realizados, teniendo en cuenta que las personas emparejadas deben tener:
- al menos un idioma en común,
- como máximo 5 años de diferencia de edad, y
- distinta nacionalidad.
