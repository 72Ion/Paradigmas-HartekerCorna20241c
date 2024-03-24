module Stack (Stack, newS, freeCellsS, stackS, netS, holdsS, popS, ciudadesEnPila)
where

import Container (Container, newC, destinationC, netC)
import Route (Route, newR, inOrderR)

import Type.Reflection
import Data.ByteString (elemIndex)

data Stack = Sta [ Container ] Int deriving (Eq, Show)

newS :: Int -> Stack                          -- construye una Pila con la capacidad indicada 
newS capacidad | capacidad >=1 =  Sta [] capacidad | otherwise = error  "la capacidad de la pila no puede ser negativa o cero"

freeCellsS :: Stack -> Int                  -- responde la celdas disponibles en la pila
freeCellsS (Sta containers capacidad) = capacidad - length containers

stackS :: Stack -> Container -> Stack         -- apila el contenedor indicado en la pila
stackS (Sta containers capacidad) container | freeCellsS (Sta containers capacidad) > 0 = Sta (container : containers) capacidad
                                            | otherwise = error "No es posible apilar un container sobre stack lleno."

--                                            | otherwise = Sta containers capacidad

netS :: Stack -> Int                          -- responde el peso neto de los contenedores en la pila
netS (Sta containers _) = foldl (\acc container -> acc + netC container) 0 containers


holdsS :: Stack -> Container -> Route -> Bool -- indica si la pila puede aceptar el contenedor considerando las ciudades en la ruta
holdsS (Sta [] _) container ruta =  inOrderR ruta (destinationC container) (destinationC container) && (netC container <= 20)
holdsS (Sta containers capacidad) container ruta = inOrderR ruta (destinationC container) (destinationC (head containers)) && (netS (Sta containers capacidad) + netC container) <= 20 && (freeCellsS (Sta containers capacidad) > 0) 

popS :: Stack -> String -> Stack              -- quita del tope los contenedores con destino en la ciudad indicada
popS (Sta [] capacity) _ = Sta [] capacity
popS (Sta (container:containers) capacity) str1
    | destinationC container == str1 = popS (Sta containers capacity) str1
    | otherwise = Sta (container:containers) capacity    


-- Funcion  personalizada para testing:
ciudadesEnPila :: Stack -> [String]
ciudadesEnPila (Sta containers _) = map destinationC containers






-- popS (Sta [] capacity) _ = Sta [] capacity  



--holdsSn :: Stack -> Container -> Route -> Bool -- indica si la pila puede aceptar el contenedor considerando las ciudades en la ruta
--holdsSn stack container checkruta | inOrderR