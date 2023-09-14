package com.avanade.history.payloads.requests;

import java.io.Serializable;

public class CharacterRequest implements Serializable {
        private String name;
        private short health;
        private short strength;
        private short defense;
        private short agility;
        private short numDice;
        private String faces;

        public String getName() {
            return name;
        }

        public short getHealth() {
            return health;
        }

        public short getStrength() {
            return strength;
        }

        public short getDefense() {
            return defense;
        }

        public short getAgility() {
            return agility;
        }

        public short getNumDice() {
            return numDice;
        }

        public String getFaces() {
            return faces;
        }
    }