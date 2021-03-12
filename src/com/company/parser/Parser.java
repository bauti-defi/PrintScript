package com.company.parser;

import java.util.List;

public class Parser {
    private List<String> linesOfCode;
    private List<Visitable> commands;
    private ConcreteVisitor visitor;
}
