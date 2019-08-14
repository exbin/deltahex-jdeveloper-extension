/*
 * Copyright (C) ExBin Project
 *
 * This application or library is free software: you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 3 of the License,
 * or (at your option) any later version.
 *
 * This application or library is distributed in the hope that it will be
 * useful, but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along this application.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.exbin.bined.operation.swing.command;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import org.exbin.bined.swing.CodeAreaCore;

/**
 * Command for editing data in text mode.
 *
 * @version 0.1.0 2016/05/17
 * @author ExBin Project (https://exbin.org)
 */
@ParametersAreNonnullByDefault
public abstract class EditDataCommand extends CodeAreaCommand {

    public EditDataCommand(CodeAreaCore codeArea) {
        super(codeArea);
    }

    @Nonnull
    @Override
    public CodeAreaCommandType getType() {
        return CodeAreaCommandType.DATA_EDITED;
    }

    @Override
    public boolean canUndo() {
        return true;
    }

    @Nonnull
    public abstract EditCommandType getCommandType();

    /**
     * Returns true if command was already used for undo action.
     *
     * @return true if undo step performed
     */
    public abstract boolean wasReverted();

    public enum EditCommandType {
        INSERT, OVERWRITE, DELETE
    }
}
