package com.knoldus.feedservice.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class SendEmailNotificationTest {
    /**
     * Method under test: {@link SendEmailNotification#canEqual(Object)}
     */
    @Test
    void testCanEqual() {
        assertFalse((new SendEmailNotification()).canEqual("Other"));
    }

    /**
     * Method under test: {@link SendEmailNotification#canEqual(Object)}
     */
    @Test
    void testCanEqual2() {
        SendEmailNotification sendEmailNotification = new SendEmailNotification();
        assertTrue(sendEmailNotification.canEqual(new SendEmailNotification()));
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link SendEmailNotification#SendEmailNotification()}
     *   <li>{@link SendEmailNotification#setHtmlBody(String)}
     *   <li>{@link SendEmailNotification#setHtmlTitle(String)}
     *   <li>{@link SendEmailNotification#setRecipients(List)}
     *   <li>{@link SendEmailNotification#setSender(String)}
     *   <li>{@link SendEmailNotification#toString()}
     *   <li>{@link SendEmailNotification#getHtmlBody()}
     *   <li>{@link SendEmailNotification#getHtmlTitle()}
     *   <li>{@link SendEmailNotification#getRecipients()}
     *   <li>{@link SendEmailNotification#getSender()}
     * </ul>
     */
    @Test
    void testConstructor() {
        SendEmailNotification actualSendEmailNotification = new SendEmailNotification();
        actualSendEmailNotification.setHtmlBody("Not all who wander are lost");
        actualSendEmailNotification.setHtmlTitle("Dr");
        ArrayList<String> stringList = new ArrayList<>();
        actualSendEmailNotification.setRecipients(stringList);
        actualSendEmailNotification.setSender("Sender");
        String actualToStringResult = actualSendEmailNotification.toString();
        assertEquals("Not all who wander are lost", actualSendEmailNotification.getHtmlBody());
        assertEquals("Dr", actualSendEmailNotification.getHtmlTitle());
        assertSame(stringList, actualSendEmailNotification.getRecipients());
        assertEquals("Sender", actualSendEmailNotification.getSender());
        assertEquals(
                "SendEmailNotification(recipients=[], sender=Sender, htmlTitle=Dr, htmlBody=Not all who wander" + " are lost)",
                actualToStringResult);
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link SendEmailNotification#SendEmailNotification(List, String, String, String)}
     *   <li>{@link SendEmailNotification#setHtmlBody(String)}
     *   <li>{@link SendEmailNotification#setHtmlTitle(String)}
     *   <li>{@link SendEmailNotification#setRecipients(List)}
     *   <li>{@link SendEmailNotification#setSender(String)}
     *   <li>{@link SendEmailNotification#toString()}
     *   <li>{@link SendEmailNotification#getHtmlBody()}
     *   <li>{@link SendEmailNotification#getHtmlTitle()}
     *   <li>{@link SendEmailNotification#getRecipients()}
     *   <li>{@link SendEmailNotification#getSender()}
     * </ul>
     */
    @Test
    void testConstructor2() {
        ArrayList<String> stringList = new ArrayList<>();
        SendEmailNotification actualSendEmailNotification = new SendEmailNotification(stringList, "Sender", "Dr",
                "Not all who wander are lost");
        actualSendEmailNotification.setHtmlBody("Not all who wander are lost");
        actualSendEmailNotification.setHtmlTitle("Dr");
        ArrayList<String> stringList1 = new ArrayList<>();
        actualSendEmailNotification.setRecipients(stringList1);
        actualSendEmailNotification.setSender("Sender");
        String actualToStringResult = actualSendEmailNotification.toString();
        assertEquals("Not all who wander are lost", actualSendEmailNotification.getHtmlBody());
        assertEquals("Dr", actualSendEmailNotification.getHtmlTitle());
        List<String> recipients = actualSendEmailNotification.getRecipients();
        assertSame(stringList1, recipients);
        assertEquals(stringList, recipients);
        assertEquals("Sender", actualSendEmailNotification.getSender());
        assertEquals("SendEmailNotification(recipients=[], sender=Sender, htmlTitle=Dr, htmlBody=Not all who wander"
                + " are lost)", actualToStringResult);
    }

    /**
     * Method under test: {@link SendEmailNotification#equals(Object)}
     */
    @Test
    void testEquals() {
        assertNotEquals(new SendEmailNotification(), null);
        assertNotEquals(new SendEmailNotification(), "Different type to SendEmailNotification");
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link SendEmailNotification#equals(Object)}
     *   <li>{@link SendEmailNotification#hashCode()}
     * </ul>
     */
    @Test
    void testEquals2() {
        SendEmailNotification sendEmailNotification = new SendEmailNotification();
        assertEquals(sendEmailNotification, sendEmailNotification);
        int expectedHashCodeResult = sendEmailNotification.hashCode();
        assertEquals(expectedHashCodeResult, sendEmailNotification.hashCode());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link SendEmailNotification#equals(Object)}
     *   <li>{@link SendEmailNotification#hashCode()}
     * </ul>
     */
    @Test
    void testEquals3() {
        SendEmailNotification sendEmailNotification = new SendEmailNotification();
        SendEmailNotification sendEmailNotification1 = new SendEmailNotification();
        assertEquals(sendEmailNotification, sendEmailNotification1);
        int expectedHashCodeResult = sendEmailNotification.hashCode();
        assertEquals(expectedHashCodeResult, sendEmailNotification1.hashCode());
    }

    /**
     * Method under test: {@link SendEmailNotification#equals(Object)}
     */
    @Test
    void testEquals4() {
        SendEmailNotification sendEmailNotification = new SendEmailNotification(new ArrayList<>(), "Sender", "Dr",
                "Not all who wander are lost");
        assertNotEquals(sendEmailNotification, new SendEmailNotification());
    }

    /**
     * Method under test: {@link SendEmailNotification#equals(Object)}
     */
    @Test
    void testEquals5() {
        SendEmailNotification sendEmailNotification = new SendEmailNotification();
        assertNotEquals(sendEmailNotification,
                new SendEmailNotification(new ArrayList<>(), "Sender", "Dr", "Not all who wander are lost"));
    }

    /**
     * Method under test: {@link SendEmailNotification#equals(Object)}
     */
    @Test
    void testEquals6() {
        SendEmailNotification sendEmailNotification = new SendEmailNotification();
        sendEmailNotification.setSender("Sender");
        assertNotEquals(sendEmailNotification, new SendEmailNotification());
    }

    /**
     * Method under test: {@link SendEmailNotification#equals(Object)}
     */
    @Test
    void testEquals7() {
        SendEmailNotification sendEmailNotification = new SendEmailNotification();
        sendEmailNotification.setHtmlTitle("Dr");
        assertNotEquals(sendEmailNotification, new SendEmailNotification());
    }

    /**
     * Method under test: {@link SendEmailNotification#equals(Object)}
     */
    @Test
    void testEquals8() {
        SendEmailNotification sendEmailNotification = new SendEmailNotification();
        sendEmailNotification.setHtmlBody("Not all who wander are lost");
        assertNotEquals(sendEmailNotification, new SendEmailNotification());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link SendEmailNotification#equals(Object)}
     *   <li>{@link SendEmailNotification#hashCode()}
     * </ul>
     */
    @Test
    void testEquals9() {
        SendEmailNotification sendEmailNotification = new SendEmailNotification(new ArrayList<>(), "Sender", "Dr",
                "Not all who wander are lost");
        SendEmailNotification sendEmailNotification1 = new SendEmailNotification(new ArrayList<>(), "Sender", "Dr",
                "Not all who wander are lost");

        assertEquals(sendEmailNotification, sendEmailNotification1);
        int expectedHashCodeResult = sendEmailNotification.hashCode();
        assertEquals(expectedHashCodeResult, sendEmailNotification1.hashCode());
    }

    /**
     * Method under test: {@link SendEmailNotification#equals(Object)}
     */
    @Test
    void testEquals10() {
        SendEmailNotification sendEmailNotification = new SendEmailNotification();

        SendEmailNotification sendEmailNotification1 = new SendEmailNotification();
        sendEmailNotification1.setSender("Sender");
        assertNotEquals(sendEmailNotification, sendEmailNotification1);
    }

    /**
     * Method under test: {@link SendEmailNotification#equals(Object)}
     */
    @Test
    void testEquals11() {
        SendEmailNotification sendEmailNotification = new SendEmailNotification();

        SendEmailNotification sendEmailNotification1 = new SendEmailNotification();
        sendEmailNotification1.setHtmlTitle("Dr");
        assertNotEquals(sendEmailNotification, sendEmailNotification1);
    }

    /**
     * Method under test: {@link SendEmailNotification#equals(Object)}
     */
    @Test
    void testEquals12() {
        SendEmailNotification sendEmailNotification = new SendEmailNotification();

        SendEmailNotification sendEmailNotification1 = new SendEmailNotification();
        sendEmailNotification1.setHtmlBody("Not all who wander are lost");
        assertNotEquals(sendEmailNotification, sendEmailNotification1);
    }
}

