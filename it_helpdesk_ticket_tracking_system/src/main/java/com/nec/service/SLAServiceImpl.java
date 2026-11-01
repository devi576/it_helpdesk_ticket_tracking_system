/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.springframework.stereotype.Service
 */
package com.nec.service;

import com.nec.model.SLA;
import com.nec.model.Ticket;
import com.nec.service.SLAService;
import org.springframework.stereotype.Service;

@Service
public class SLAServiceImpl
implements SLAService {
    @Override
    public void applySlaToTicket(Ticket t) {
    }

    @Override
    public SLA getByPriority(String priority) {
        return null;
    }
}

