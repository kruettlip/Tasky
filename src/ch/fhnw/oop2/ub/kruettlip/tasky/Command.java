package ch.fhnw.oop2.ub.kruettlip.tasky;

import java.util.Arrays;
import java.util.List;

public class Command {
    private List<String> aliases;
    private String description;

    public Command(String description, String... aliases)
    {
        this.setAliases(Arrays.asList(aliases));
        this.setDescription(description);
    }

    public String getAliases() {
        return String.join(", ", aliases);
    }

    public boolean hasAlias(String alias) {
        return aliases.contains(alias);
    }

    public String getDescription() {
        return description;
    }

    private void setAliases(List<String> aliases) {
        this.aliases = aliases;
    }

    private void setDescription(String description) {
        this.description = description;
    }
}
