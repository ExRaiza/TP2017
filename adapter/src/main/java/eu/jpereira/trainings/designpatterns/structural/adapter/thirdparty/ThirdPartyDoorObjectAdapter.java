package eu.jpereira.trainings.designpatterns.structural.adapter.thirdparty;

import eu.jpereira.trainings.designpatterns.structural.adapter.exceptions.CodeMismatchException;
import eu.jpereira.trainings.designpatterns.structural.adapter.exceptions.IncorrectDoorCodeException;
import eu.jpereira.trainings.designpatterns.structural.adapter.model.Door;
import eu.jpereira.trainings.designpatterns.structural.adapter.thirdparty.exceptions.CannotChangeCodeForUnlockedDoor;
import eu.jpereira.trainings.designpatterns.structural.adapter.thirdparty.exceptions.CannotChangeStateOfLockedDoor;
import eu.jpereira.trainings.designpatterns.structural.adapter.thirdparty.exceptions.CannotUnlockDoorException;

public class ThirdPartyDoorObjectAdapter implements Door {

    private ThirdPartyDoor newDoor = new ThirdPartyDoor();

    @Override
    public void open(String code) throws IncorrectDoorCodeException {
        try {
            newDoor.unlock(code);
            newDoor.setState(ThirdPartyDoor.DoorState.OPEN);
        } catch (CannotChangeStateOfLockedDoor cannotChangeStateOfLockedDoor) {
            System.out.println("Door locked");
        } catch (CannotUnlockDoorException e) {
            throw new IncorrectDoorCodeException();
        }
    }

    @Override
    public void close() {
        if (newDoor.getState() == ThirdPartyDoor.DoorState.CLOSED) {
            System.out.println("Closed already");
        } else {
            try {
                newDoor.setState(ThirdPartyDoor.DoorState.CLOSED);
            } catch (CannotChangeStateOfLockedDoor cannotChangeStateOfLockedDoor) {
                System.out.println("Door locked");
            }
        }
    }

    @Override
    public boolean isOpen() {
        if (newDoor.getState() == ThirdPartyDoor.DoorState.OPEN) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void changeCode(String oldCode, String newCode, String newCodeConfirmation) throws IncorrectDoorCodeException, CodeMismatchException {

        if (newCode.equals(newCodeConfirmation)) {
            try {
                newDoor.unlock(oldCode);
            } catch (CannotUnlockDoorException e) {
                System.out.println("Bad Code");
            }
            if (newDoor.getLockStatus() == ThirdPartyDoor.LockStatus.UNLOCKED) {
                try {
                    newDoor.setNewLockCode(newCode);
                } catch (CannotChangeCodeForUnlockedDoor cannotChangeCodeForUnlockedDoor) {
                    System.out.println("Locked Doors");
                }
            } else {
                throw new IncorrectDoorCodeException();
            }
        } else {
            throw new CodeMismatchException();
        }
    }

    @Override
    public boolean testCode(String code) {
        try {
            newDoor.unlock(code);
        } catch (CannotUnlockDoorException e) {
        }
        if (newDoor.getLockStatus() == ThirdPartyDoor.LockStatus.UNLOCKED) {
            newDoor.lock();
            return true;
        } else {
            newDoor.lock();
            return false;
        }
    }
}
