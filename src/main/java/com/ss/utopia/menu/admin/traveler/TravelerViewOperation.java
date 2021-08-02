package com.ss.utopia.menu.admin.traveler;

import com.ss.utopia.domain.User;
import com.ss.utopia.menu.AbstractViewOperation;

import static com.ss.utopia.util.StringUtils.newLine;

public class TravelerViewOperation extends AbstractViewOperation<User> {

    TravelerViewOperation(User traveler) {
        super(traveler);
    }

    @Override
    protected String formatObject(User traveler) {
        return "  Give name:\t" + traveler.getGivenName() + newLine()
                + "  Family name:\t" + traveler.getFamilyName() + newLine()
                + "  Username:\t\t" + traveler.getUsername() + newLine()
                + "  Email:\t\t" + traveler.getEmail() + newLine()
                + "  Phone:\t\t" + traveler.getPhone() + newLine();
    }
}
