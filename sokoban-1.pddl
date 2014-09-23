{\rtf1\ansi\ansicpg1252\cocoartf1187\cocoasubrtf370
{\fonttbl\f0\fswiss\fcharset0 Helvetica;}
{\colortbl;\red255\green255\blue255;}
\margl1440\margr1440\vieww10800\viewh8400\viewkind0
\pard\tx720\tx1440\tx2160\tx2880\tx3600\tx4320\tx5040\tx5760\tx6480\tx7200\tx7920\tx8640\pardirnatural

\f0\fs24 \cf0 (define (:problem sokoban-1)\
	(:domain sokoban-domain)\
	(:objects A1 A2 B1 C1 C2 C3 C4 D1 D2 D3 D4 E1 E2 Bot1 Block1 up down left right)\
	(: init (NextTo A1 A2 right) (NextTo A2 A1 left) (NextTo A2 B1 down) (NextTo B1 A2 up) (NextTo B1 C2 down) (NextTo C1 C2 right) (NextTo C1 D1 down) (NextTo C2 C1 left) (NextTo C2 C3 right) (NextTo C2 B1 up) (NextTo C2 D2 down) (NextTo C3 C2 left) (NextTo C3 C4 right) (NextTo C3 D3 down) (NextTo C4 C3 left) (NextTo C4 D4 down) (NextTo D1 C1 up) (NextTo D1 D2 right) (NextTo D1 E1 down) (NextTo D2 D1 left) (NextTo D2 C2 up) (NextTo D2 D3 right) (NextTo D2 E2 down) (NextTo D3 D2 left) (NextTo D3 C3 up) (NextTo D3 D4 right) (NextTo D4 C4 up) (NextTo D4 D3 left) (NextTo E1 D1 up) (NextTo E1 E2 right) (NextTo E2 E1 left) (NextTo E2 D2 up) (Filled D3) (Filled B1) (At Bot1 B1) (At Block1 D3) (BLOCK Block1) (BOT Bot1))\
	(:goal (At Block1 A2))}