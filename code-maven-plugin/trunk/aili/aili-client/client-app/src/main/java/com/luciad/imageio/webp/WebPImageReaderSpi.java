package com.luciad.imageio.webp;

import javax.imageio.ImageReader;
import javax.imageio.spi.ImageReaderSpi;
import javax.imageio.stream.ImageInputStream;
import java.io.IOException;
import java.nio.ByteOrder;
import java.util.Arrays;
import java.util.Locale;

/**
 *
 */
public class WebPImageReaderSpi extends ImageReaderSpi {
  private static final byte[] RIFF = new byte[]{ 'R', 'I', 'F', 'F' };
  private static final byte[] WEBP = new byte[]{ 'W', 'E', 'B', 'P' };
  private static final byte[] VP8_ = new byte[]{ 'V', 'P', '8', ' ' };
  private static final byte[] VP8X = new byte[]{ 'V', 'P', '8', 'X' };

  public WebPImageReaderSpi() {
    super(
        "Luciad",
        "1.0",
        new String[]{ "WebP", "webp" },
        new String[]{ "webp" },
        new String[]{ "image/webp" },
        WebPReader.class.getName(),
        new Class[] { ImageInputStream.class },
        new String[]{ WebPImageWriterSpi.class.getName() },
        false,
        null,
        null,
        null,
        null,
        false,
        null,
        null,
        null,
        null
    );
  }

  @Override
  public ImageReader createReaderInstance( Object extension ) throws IOException {
    return new WebPReader( this );
  }

  @Override
  public boolean canDecodeInput( Object source ) throws IOException {
    if ( !( source instanceof ImageInputStream ) ) {
      return false;
    }

    ImageInputStream stream = ( ImageInputStream ) source;
    byte[] b = new byte[ 4 ];
    ByteOrder oldByteOrder = stream.getByteOrder();
    stream.mark();
    stream.setByteOrder( ByteOrder.LITTLE_ENDIAN );

    try {
      stream.readFully( b );
      if ( !Arrays.equals( b, RIFF ) ) {
        return false;
      }
      long chunkLength = stream.readUnsignedInt();
      long streamLength = stream.length();
      if ( streamLength != -1 && streamLength != chunkLength + 8 ) {
        return false;
      }
      stream.readFully( b );
      if ( !Arrays.equals( b, WEBP ) ) {
        return false;
      }

      stream.readFully( b );
      if ( !Arrays.equals( b, VP8_ ) && !Arrays.equals( b, VP8X ) ) {
        return false;
      }
    } finally {
      stream.setByteOrder( oldByteOrder );
      stream.reset();
    }

    return true;
  }

  @Override
  public String getDescription( Locale locale ) {
    return "WebP Reader";
  }
}