/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.springframework.stereotype.Service
 */
package com.nec.service;

import com.nec.model.SLA;
import com.nec.model.Ticket;
import org.springframework.stereotype.Service;

@Service
public interface SLAService {
    public void applySlaToTicket(Ticket var1);

    public SLA getByPriority(String var1);
}

