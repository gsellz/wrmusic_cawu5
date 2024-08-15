package com.example.wrmusic_project;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class TopPagerAdapter extends FragmentStateAdapter {

    public TopPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return TopFragment.newInstance(
                        new String[]{"1", "2", "3"},
                        new String[]{"Random Access Memories", "The Dark Side Of The Moon", "Good Kid, M.A.A.d City"},
                        new String[]{"Daft Punk", "Pink Floyd", "Kendrick Lamar"},
                        new String[]{"Blending electronic and disco, this album offers a nostalgic yet innovative sonic experience with infectious grooves and collaborations with iconic artists.", "A profound journey through introspective themes of life, death, and the human condition, set to hauntingly beautiful music and innovative soundscapes.", "Captures the struggles and triumphs of inner-city life with raw lyricism and captivating storytelling."},
                        new String[]{"Funk", "Rock", "Hip Hop"},
                        new String[]{"$10", "$20", "$30"},
                        new int[]{R.drawable.image1, R.drawable.image2, R.drawable.image3},
                        new int[]{13, 10, 15});
            case 1:
                return TopFragment.newInstance(
                        new String[]{"1", "2", "3"},
                        new String[]{"Nevermind", "The Dark Side Of The Moon", "AM"},
                        new String[]{"Nirvana", "Pink Floyd", "Arctic Monkeys"},
                        new String[]{"Revolutionized the music industry with its pop, funk, and R&B hits, solidifying the artist's status as a cultural icon.", "A poignant exploration of love and heartbreak, showcasing harmonious melodies and captivating songwriting.", "Catapulted a music genre into the mainstream with its raw energy and iconic singles, forever changing the landscape of popular music."},
                        new String[]{"Funk", "Pop","Rock"},
                        new String[]{"$20", "$10", "$10"},
                        new int[]{R.drawable.image4, R.drawable.image5, R.drawable.image6},
                        new int[]{9, 11, 12});
            case 2:
                return TopFragment.newInstance(
                        new String[]{"1", "2", "3"},
                        new String[]{"1989", "Future Nostalgia", "Chromatica"},
                        new String[]{"Taylor Swift", "Dua Lipa", "Lady Gaga"},
                        new String[]{"Introspective exploration of life, death, and the human condition with hauntingly beautiful music.", "A stylish blend of rock and R&B, evoking the allure of late-night adventures and romantic entanglements.", "Creates a dark and atmospheric sonic landscape with elements of trip-hop, electronica, and rock, captivating listeners with its brooding soundscapes."},
                        new String[]{"Rock", "Rock", "Electronic"},
                        new String[]{"$20", "$30", "$40"},
                        new int[]{R.drawable.image7, R.drawable.image8, R.drawable.image9},
                        new int[]{10, 12, 11});
            default:
                return null;
        }
    }

    @Override
    public int getItemCount() {
        return 3; // Jumlah fragment yang akan ditampilkan
    }
}
