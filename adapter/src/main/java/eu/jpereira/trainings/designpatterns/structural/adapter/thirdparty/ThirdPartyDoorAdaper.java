package eu.jpereira.trainings.designpatterns.structural.adapter.thirdparty;

import eu.jpereira.trainings.designpatterns.structural.adapter.exceptions.CodeMismatchException;
import eu.jpereira.trainings.designpatterns.structural.adapter.exceptions.IncorrectDoorCodeException;
import eu.jpereira.trainings.designpatterns.structural.adapter.model.Door;
import eu.jpereira.trainings.designpatterns.structural.adapter.thirdparty.exceptions.CannotUnlockDoorException;

public class ThirdPartyDoorAdaper extends ThirdPartyDoor implements Door {

    private LockStatus status = LockStatus.LOCKED;
    private String code = DEFAULT_CODE;
    private DoorState state = DoorState.CLOSED;

    @Override
    public void open(String code) throws IncorrectDoorCodeException {
        if (code.equals(this.code)) {
            this.status = LockStatus.UNLOCKED;
            this.state = DoorState.OPEN;
        } else {
            throw new IncorrectDoorCodeException();
        }
    }

    @Override
    public void close() {
        if (this.status.equals(LockStatus.LOCKED)) {
            System.out.println("Door locked");
        } else if (this.state == DoorState.CLOSED) {
            System.out.println("Closed already");
        } else {
            state = DoorState.CLOSED;
        }
    }

    @Override
    public boolean isOpen() {
        if (this.state == DoorState.OPEN) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void changeCode(String oldCode, String newCode, String newCodeConfirmation) throws IncorrectDoorCodeException, CodeMismatchException {

        if (newCode.equals(newCodeConfirmation)) {
            if (oldCode.equals(this.code)) {
                this.code = newCode;
            } else {
                throw new IncorrectDoorCodeException();
            }
        } else {
            throw new CodeMismatchException();
        }
    }

    @Override
    public boolean testCode(String code) {
        if (code.equals(this.code)) {
            return true;
        } else {
            return false;
        }
    }
}
