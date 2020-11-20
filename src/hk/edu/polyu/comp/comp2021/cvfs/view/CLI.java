package hk.edu.polyu.comp.comp2021.cvfs.view;

import hk.edu.polyu.comp.comp2021.cvfs.controller.CVFS;
import hk.edu.polyu.comp.comp2021.cvfs.model.fileSystem.File;


import java.io.IOException;
import java.util.Arrays;
import java.util.IllegalFormatException;
import java.util.Scanner;

enum CommandType {
    newDisk, newDoc, newDir, delete, rename, changeDir,
    list, rList,
    newSimpleCri, newNegation, newBinaryCri,
    printAllCriteria, search, rSearch,
    store, load, undo, redo, quit;
}


class Command {
    CommandType command;
    String[] parameters;

    Command(String input) {
        if (input == null){
            throw new IllegalArgumentException("Empty input!");
        }
        String inputAfterTrim = input.trim();

        if (inputAfterTrim.length() == 0){
            throw new IllegalArgumentException("Empty input!");
        }
        String [] inputAfterSplit = inputAfterTrim.split(" ");

        this.command = CommandType.valueOf(inputAfterSplit[0]);
        if (inputAfterSplit.length > 1){
            this.parameters = Arrays.copyOfRange(inputAfterSplit,1,inputAfterSplit.length);
        }
    }
}

public class CLI {
    private String userInput;
    CVFS system;
    Command command;
    Scanner scanner;

    public CLI(){
        this.system = new CVFS();
        this.scanner = new Scanner(System.in);
    }

    public void scanInput() {
        String input = null;

        System.out.print("D:IdeaProjects\\MyCode>");
        if (scanner.hasNextLine()) {
            input = scanner.nextLine();
//            System.out.println(input);
        }

        assert input != null;
        this.command = new Command(input);
    }

    public void manageCommand() {
        switch (this.command.command) {
            case newDisk:
                system.newDisk(Integer.getInteger(command.parameters[0]));
                break;
            case newDoc:
                system.newDoc(command.parameters[0], command.parameters[1], command.parameters[2]);
                break;
            case newDir:
                system.newDir(command.parameters[0]);
                break;
            case delete:
                system.delFile(command.parameters[0]);
                break;
            case rename:
                system.rename(command.parameters[0], command.parameters[1]);
                break;
            case changeDir:
                system.changeDir(command.parameters[0]);
                break;
            case list:
                for (File f:system.list()){
                    System.out.println(f.getFullName());
                }

                break;
            case rList:
                system.rlist();
                break;
            case newSimpleCri:
                system.newSimpleCri(command.parameters[0], command.parameters[1],
                        command.parameters[2], command.parameters[3]);
                break;

            case newNegation:
                system.newNegation(command.parameters[0], command.parameters[1]);
            case newBinaryCri:
                system.newBinaryCri(command.parameters[0], command.parameters[1], command.parameters[2], command.parameters[3]);
                break;
            case printAllCriteria:
                system.printAllCriteria();
            case search:
                system.searchByCriterion(command.parameters[0]);
                break;
            case rSearch:
                system.rSearchByCriterion(command.parameters[0]);
                break;
            case store:
                system.store();
            case load:
                system.load();
                break;
            case undo:
                system.undo();
            case redo:
                system.redo();
        }
    }
}

