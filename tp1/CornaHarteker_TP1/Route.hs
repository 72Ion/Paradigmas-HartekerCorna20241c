module Route (Route, newR, inOrderR) 
 where

import Data.List (elemIndex)
import Data.Maybe
import Data.Char
import GHC.Exts.Heap (Closure)
import Container

data Route = Rou [ String ] deriving (Eq, Show)

newR :: [ String ] -> Route                    -- construye una ruta segun una lista de ciudades
newR ciudades | length(ciudades)>0 =  Rou ciudades | otherwise = error "Una ruta no puede estar vacia"

inOrderR :: Route -> String -> String -> Bool  -- indica si la primer ciudad consultada esta antes que la segunda ciudad en la ruta
inOrderR (Rou ciudades) str1 str2 = (elemIndex str1 ciudades <= elemIndex str2 ciudades) && ((elem str1 ciudades) && (elem str2 ciudades))
