module Vessel ( Vessel, newV, freeCellsV, loadV, unloadV, netV, ciudadesEnVessel)
 where

import Data.List (elemIndex)
import Data.Maybe
import Data.Char
import GHC.Exts.Heap (Closure)


import Container
import Stack 
import Route
import Route (inOrderR)

data Vessel = Ves [ Stack ] Route deriving (Eq, Show)

newV :: Int -> Int -> Route -> Vessel  -- construye un barco según una cantidad de bahias, la altura de las mismas y una ruta
newV cant depth xs_ciudades | cant >= 1 && depth >= 1 = Ves (replicate cant (newS depth)) xs_ciudades 
                         | otherwise = error "No se puede generar un Vessel con bahias y/o alturas negativas o ceros"


freeCellsV :: Vessel -> Int            -- responde la celdas disponibles en el barco
freeCellsV (Ves bahias _ ) = sum (map freeCellsS bahias)



stackV :: [Stack] -> Route -> Container -> [Stack]
stackV [] _ _ = error "Este contenedor no se puede cargar!"
stackV (stack:lista_stack) ruta cont 
    | holdsS stack cont ruta = (stackS stack cont) : lista_stack 
    | otherwise = stack : stackV lista_stack ruta cont


stackUnloadV :: [Stack] -> String -> [Stack]
stackUnloadV [] str1 = []
stackUnloadV (stack:rest) str1 = popS stack str1 : (stackUnloadV rest str1)


loadV :: Vessel -> Container -> Vessel
loadV (Ves lista_stack ruta) cont = Ves (stackV lista_stack ruta cont) ruta 



unloadV :: Vessel -> String -> Vessel  -- responde un barco al que se le han descargado los contenedores que podían descargarse en la ciudad
unloadV (Ves bahias ciudades) str1 | sum (map netS bahias) == 0 = error "Buque Vacio, no descargar"
                                   | (inOrderR ciudades str1 str1) == False = error "Ciudad no pertenece a la ruta"
                                   | otherwise = Ves (stackUnloadV bahias str1) ciudades

netV :: Vessel -> Int -- responde el peso neto en toneladas de los contenedores en el barco
netV (Ves bahias ruta) = sum (map netS bahias)

-- Una funcion hecha para testing
ciudadesEnVessel :: Vessel -> [[String]]
ciudadesEnVessel (Ves stacks _) = map ciudadesEnPila stacks
