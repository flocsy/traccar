package org.traccar.protocol;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.junit.Test;

public class Tk103ProtocolDecoderTest {

    @Test
    public void testDecode() throws Exception {

        Tk103ProtocolDecoder decoder = new Tk103ProtocolDecoder(null);
        decoder.setDataManager(new TestDataManager());

        assertNull(decoder.decode(null, null, "(090411121854BP0000001234567890HSO"));

        assertNotNull(decoder.decode(null, null,
                "(035988863964BP05000035988863964110524A4241.7977N02318.7561E000.0123536356.5100000000L000946BB"));

        assertNotNull(decoder.decode(null, null,
                "(013632782450BP05000013632782450120803V0000.0000N00000.0000E000.0174654000.0000000000L00000000"));

    }

}
