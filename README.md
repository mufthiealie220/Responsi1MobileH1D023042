Nama : Mufthie Alie
NIM  : H1D023042
Shift Awal : B
Shift Akhir : A

# Video

https://github.com/user-attachments/assets/43aed7d5-2097-4f22-9039-f8ebfd9d3e12

# Penjelasan
untuk awalannya dari ViewModel, tepatnya di TeamViewModel. Di situ ada fungsi fetchTeamData() yang dimana tugasnya manggil data dari API football-data.org. Nah, biar bisa dapet datanya, TeamViewModel manggil RetrofitInstance.api.getTeam() yang udah disiapin sebelumnya.
RetrofitInstance ini kayak “mesin penghubung” ke internetnya. Dia dibangun pakai Retrofit Builder dengan BASE_URL dari file Constants. Nah, di situ juga disimpan API_TOKEN dan TEAM_ID.
Pas fetchTeamData() dijalankan, Retrofit otomatis bikin request ke URL https://api.football-data.org/v4/teams/15 dengan header berisi token API-nya.Dari situ, servernya (football-data.org) bakal balikin response JSON yang isinya data tim — kayak nama klub, pelatih (coach), daftar pemain (squad), warna klub, stadion, dan lain-lain.
Nah, JSON ini diubah jadi objek Kotlin otomatis sama GsonConverterFactory, terus hasilnya disimpan ke LiveData _teamData di ViewModel.data yang udah masuk ke LiveData ini langsung bisa dipantau (observe) sama activity lain kayak DaftarPemain dan HeadCoach.Di DaftarPemain, datanya diambil dari teamData.observe, terus pemain-pemainnya ditampilin dalam bentuk RecyclerView lewat PemainAdapter.
Adapter ini ngatur tampilan tiap pemain sesuai posisinya — jadi misalnya Goalkeeper warnanya kuning, Defender biru, Midfielder hijau, dan Forward merah.
Kalau user klik salah satu pemain, bakal muncul BottomSheet (PemainDetailFragment) yang nampilin detail kayak nama, posisi, sama kebangsaan.Sementara di HeadCoach, activity-nya juga observe teamData yang sama, tapi cuma ambil bagian coach. Jadi dia nampilin nama pelatih, tanggal lahir, kebangsaan, plus ada bagian “cerita” dan “prestasi” si pelatih yang ditulis manual biar kelihatan lebih menarik.
