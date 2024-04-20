package JavaStreams;

import java.util.Random;
 public record SeatReservation(char rowMarker, int seatNumber, boolean isReserved) {
        public SeatReservation(char rowMarker, int seatNumber) {
            this(rowMarker, seatNumber, new Random().nextBoolean());
        }
//    @Override
//    public String toString() {
//        return "%c%03d %.0f".formatted(rowMarker,seatNumber,price);
//    }
    }