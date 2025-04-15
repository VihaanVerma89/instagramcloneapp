# Instagram Clone App

An Instagram-like Android application built with Jetpack Compose that replicates the core UI and functionality of Instagram.

## Features

- **Feed Display**: Scrollable feed with posts similar to Instagram
- **Stories**: Horizontal scrollable stories section at the top
- **Post Interactions**:
    - Double-tap to like with animation
    - Comment, like, save, and share actions
    - View counts for likes and comments
- **Pull-to-Refresh**: Refresh the feed with a pull-down gesture
- **Content Variety**:
    - Regular posts with images and captions
    - Sponsored posts
    - Suggested user recommendations
- **Navigation**: Instagram-like bottom navigation bar
- **Music Player**: Mini player for audio content

## Screenshots

[Add screenshots of your app here]

## Tech Stack

- **Language**: Kotlin
- **UI Framework**: Jetpack Compose
- **Architecture**: MVVM (Model-View-ViewModel)
- **State Management**: Kotlin Flow
- **Image Loading**: Coil 3
- **UI Components**: Material 3
- **Icons**: Material Icons Extended

## Getting Started

### Prerequisites

- Android Studio Iguana or newer
- Kotlin 2.0+
- Minimum SDK: 24 (Android 7.0)
- Target SDK: 34 (Android 14)

### Installation

1. Clone the repository:
git clone https://github.com/yourusername/instagram-clone.git

2. Open the project in Android Studio

3. Sync the Gradle files and install necessary dependencies

4. Run the app on an emulator or physical device

## Project Structure

- **app/src/main/java/com/example/instagramapp/** - Main source code
- **MainActivity.kt** - Entry point of the application
- **FeedViewModel.kt** - ViewModel for the feed data
- **InstagramFeed.kt** - UI components for the Instagram feed
- **Post.kt** - Data models

## Future Enhancements

- User authentication
- Profile page
- Search functionality
- Post creation
- Direct messaging
- Video content
- Dark mode support

## Contributing

Contributions are welcome! Feel free to submit a Pull Request.

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Acknowledgements

- Instagram for design inspiration
- [Jetpack Compose](https://developer.android.com/jetpack/compose) for the UI toolkit
- [Coil](https://coil-kt.github.io/coil/) for image loading