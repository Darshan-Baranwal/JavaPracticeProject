package JavaStreams;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Stream;

public class SeatMain {
        public static void main(String[] args) {
//            generateSeats()
//                    .forEach(System.out::println);
            createSeats();
        }

    private static void createSeats() {
       SeatReservation[] seatReservations = new SeatReservation[5];
        Arrays.setAll(seatReservations, i-> new SeatReservation((char) ('A'+i/10), i%10+1));
        Arrays.stream(seatReservations).forEach(System.out::println);
        long reservedSeats = Arrays.stream(seatReservations).filter(SeatReservation::isReserved).count();
        System.out.println("Reserved Seats :" + reservedSeats);
        boolean hasBookings = Arrays.stream(seatReservations).anyMatch(s -> s.isReserved());
        System.out.println("Seats has bookings :"+ hasBookings);

        boolean fullyBooked = Arrays.stream(seatReservations).allMatch(SeatReservation::isReserved);
        System.out.println("Is seats fully booked :"+fullyBooked);

        boolean eventWashedOut = Arrays.stream(seatReservations).noneMatch(SeatReservation::isReserved);
        System.out.println("Is event washout : "+eventWashedOut);
    }

    public static Stream generateSeats() {
            int maxSeats = 100;
            int seatNumber = 10;
            var stream = Stream.iterate(0, i->i<maxSeats, i->i+1)
                    .map(i-> new Seat((char) ('A'+i/seatNumber),
                            i%seatNumber+1))
                    .sorted(Comparator.comparing(Seat::price).thenComparing(Seat::toString));
//                    .mapToDouble(Seat::price)
//                    .mapToObj("%f"::formatted); // converting DoubleStream (or Int/Long Stream) to Object Stream(Eg. String)

            return stream;
        }
    }
