(define (problem sokoban-3)
    (:domain sokoban-domain)
    (:objects bot block1 block2 block3 A1 A2 A3 B1 B2 C1 C2 D1 D2 D3 D4 D5 D6 D7 D8 D9 E1 E2 E3 E4 F1 F2 F3 F4 F5 F6 F7 LEFT RIGHT UP DOWN)
    (:init (BOT bot) (BLOCK block1) (BLOCK block2) (BLOCK block3)
        (At bot D2) (At block1 D8) (At block2 D6) (At block3 D4)
        (Filled D2) (Filled D4) (Filled D6) (Filled D8) 
        (NextTo A2 A3 RIGHT)
        (NextTo A2 A1 LEFT) 
     
        (NextTo B1 A1 UP) (NextTo B2 A3 UP)
        (NextTo B1 C1 DOWN) (NextTo B2 C2 DOWN)
        (NextTo C1 B1 UP) (NextTo C2 B2 UP)
        (NextTo C1 D7 DOWN) (NextTo C2 D9 DOWN)
        (NextTo D7 C1 UP) 
        (NextTo D2 D3 RIGHT) (NextTo D3 D4 RIGHT)
        (NextTo D4 D5 RIGHT) (NextTo D5 D6 RIGHT) (NextTo D6 D7 RIGHT)
        (NextTo D7 D8 RIGHT) (NextTo D8 D9 RIGHT)
        (NextTo D8 D7 LEFT) (NextTo D7 D6 LEFT)
        (NextTo D6 D5 LEFT) (NextTo D5 D4 LEFT) (NextTo D4 D3 LEFT)
        (NextTo D3 D2 LEFT) (NextTo D2 D1 LEFT)
        
        (NextTo D7 E4 DOWN) 
        (NextTo E1 D1 UP) (NextTo E2 D3 UP) (NextTo E3 D5 UP)
        (NextTo E4 D7 UP)
        (NextTo E1 F1 DOWN) (NextTo E2 F3 DOWN) (NextTo E3 F5 DOWN)
        (NextTo E4 F7 DOWN)
        
        (NextTo F2 F3 RIGHT) (NextTo F3 F4 RIGHT)
        (NextTo F4 F5 RIGHT) (NextTo F5 F6 RIGHT) (NextTo F6 F7 RIGHT)
        (NextTo F7 F6 LEFT) (NextTo F6 F5 LEFT) (NextTo F5 F4 LEFT)
        (NextTo F4 F3 LEFT) (NextTo F3 F2 LEFT) )

    (:goal (and (At block1 A1) (At block2 B1) (At block3 C1))))
