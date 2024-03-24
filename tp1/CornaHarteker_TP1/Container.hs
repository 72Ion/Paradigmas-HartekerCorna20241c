
module Container (Container, newC, destinationC, netC)
    where

import Data.Maybe
import Data.Char
import GHC.Exts.Heap (Closure)


data Container = Con String Int deriving (Eq, Show)

newC :: String -> Int -> Container -- construye un Contenedor dada una ciudad de destino y un peso en toneladas
newC ciudad peso | peso >= 1 = Con ciudad peso | otherwise = error "No se puede peso negativo" 

destinationC :: Container -> String  -- responde la ciuda destino del contenedor
destinationC (Con ciudad _) = ciudad

netC :: Container -> Int             -- responde el peso en toneladas del contenedor
netC (Con _ peso) = peso
