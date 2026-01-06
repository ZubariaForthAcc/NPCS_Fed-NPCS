# NPCS_Fed-NPCS
## Reproducibility Overview

This repository provides full instructions to reproduce the experimental results
reported in the paper for both NPCS (centralized) and Fed-NPCS (federated).

The reproducibility pipeline consists of:
1. Dataset generation (WatDiv, Wikidata, FedShop)
2. Provenance encoding using multiple reification schemes
3. Query rewriting using NPCS / Fed-NPCS
4. Query execution on GraphDB or Stardog
5. Collection of execution times and result sizes

Detailed, step-by-step instructions are provided in the following locations:

- Dataset generation & reification:
  - `docs/datasets/`
- Centralized experiments (NPCS):
  - `NPCS-main/README.org`
- Federated experiments (Fed-NPCS):
  - `Fed-NPCS-main/README.org`
