package ru.bardinpetr.itmo.lab5.models.commands;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import ru.bardinpetr.itmo.lab5.models.commands.base.Command;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
@NoArgsConstructor
public class ExitCommand extends Command {
    @Override
    public String getType() {
        return "exit";
    }
}
