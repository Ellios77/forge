# AGENTS.md for Forge Card Scripting – Card Scripting Agent

This document outlines guidelines and operational procedures for the Open AI Codex coding agent. Open AI Codex's primary task is to generate Magic: The Gathering card scripts in Forge format by analyzing existing card scripts from the Forge repository.

## Scope and Environment

- **Reference-Driven**: Open AI Codex relies on existing Forge card scripts as examples to generate new scripts.
    
- **Input Data**: The agent uses supplied card oracle text.
    

## Input and Output Expectations

- **Input**:
    
    - Target card(s) name with supplied Oracle text.
        
    - A list of up to five similar reference cards (provided by searching existing card scripts within the repo).
        
- **Output**:
    
    - A Forge-compatible script in `.txt` format for the target card.
        
    - Syntax, structure, and logic modeled after the reference scripts.
        

## Script Construction Process

### 1. Analyze Reference Cards

Open AI Codex must:

- Identify similar mechanics, keywords, and trigger modes.
    
- Compare structural elements such as:
    
    - Ability factories (`A:`, `T:`, `S:`)
        
    - Spell costs (`Cost$`)
        
    - Effects and their definitions (`DB$`, `SVar:`)
        
    - Static vs. triggered behavior
        

### 2. Construct New Card Script

Using the structure and vocabulary of the reference cards, Open AI Codex should:

- Mirror correct Forge syntax for triggers, abilities, and costs.
    
- Ensure logical chaining of sub-abilities (`SubAbility$`) and variable references (`SVar:`).
    
- Reflect oracle text as closely as possible using Forge scripting semantics.
    
- Default to conservative interpretations if ambiguity exists, prioritizing playability over complexity. However, accuracy to the oracle text is the desired outcome.
    

### 3. Formatting and Naming

- Card files must be named `{Card_Name_With_Underscores}.txt` and placed under `/cardsfolder/{first_letter or requested path}/`.
    
- Maintain consistent indentation and formatting conventions from reference cards.
    
- Include comments sparingly—only if necessary for clarity or deviations. Include comments if no solution can be found to script a function of the card and suggest engine update support.
    

## Coding Standards

- **Defined Targets**: Use `Defined$ Self`, `You`, `Opponent`, etc., consistent with patterns in reference files. Use ValidTgt$ appropriately as well.
    
- **Trigger Descriptions**: Always include `TriggerDescription$` and `SpellDescription$` for user-facing clarity.
    
- **Conditions and Restrictions**: Emulate condition checks (`CheckSVar`, `Condition$`, etc.) based on how they appear in examples.
    
- **Safety Defaults**:
    
    - No external references
        
    - No assumptions beyond observable patterns in examples
        
    - Simple, linear scripting unless a more complex pattern is explicitly modeled
        

## Agent Constraints

    
- **No Java Editing**: The agent is not to modify source code in `/src`.
    

## Summary

Open AI Codex is a synthesis-driven agent for Forge card script generation. It uses example-based reasoning to construct syntactically correct and functionally aligned card definitions. Its value lies in automation, consistency, and adherence to proven scripting patterns in an environment.
